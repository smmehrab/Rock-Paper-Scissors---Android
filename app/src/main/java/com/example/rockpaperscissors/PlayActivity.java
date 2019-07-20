package com.example.rockpaperscissors;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gaurav.gesto.OnGestureListener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;


public class PlayActivity extends AppCompatActivity {

    private TextView userPercentageTxt;
    private TextView andyPercentageTxt;

    private Button userScoreBtn;
    private Button andyScoreBtn;

    private ImageView userMove;
    private ImageView andyMove;

    private int userState=0;
    private int andyState=0;

    private int userScore=0;
    private int andyScore=0;

    private int userWinCount = 0;
    private int andyWinCount =0;
    private int tieCount = 0;

    private double userPercentageVal=0;
    private double andyPercentageVal=0;
    private double tiePercentageVal = 0;

    int playState = 0;

    private LinearLayout swipeArea;

    boolean doubleBackToExitPressedOnce = false;
    boolean swipeExit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        userMove = (ImageView) findViewById(R.id.userMove);
        andyMove = (ImageView) findViewById(R.id.andyMove);

        userScoreBtn = (Button) findViewById(R.id.userScore);
        andyScoreBtn = (Button) findViewById(R.id.andyScore);

        userPercentageTxt = (TextView) findViewById(R.id.userPercentage);
        andyPercentageTxt = (TextView) findViewById(R.id.andyPercentage);

        userScoreBtn.setText(Integer.toString(userScore));
        andyScoreBtn.setText(Integer.toString(andyScore));

        swipeArea = (LinearLayout) findViewById(R.id.swipeArea);


        swipeArea.setOnTouchListener(new OnGestureListener(this) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                handleExitRequest();
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                //showMatchDetails();
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if(playState==0){
                    handleMakeMove();
                }
                else if(playState==1){
                    handleNewRound();
                }
            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                handleNewMatch();
            }

            @Override
            public void onClick() {
                super.onClick();
                handleSingleTap();
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                handleDoubleTap();
            }

            @Override
            public void onLongClick() {
                super.onLongClick();
            }}

        );
    }

    public void handleSingleTap(){
        if(playState==1){
            //showToast("Double Tap to Play Again");
        }
        else if(playState==0){
            handleChangeMove();
        }
    }

    public void handleDoubleTap(){
        if(playState==1){
            //handleNewRound();
        }
    }

    public void handleNewRound(){
        showToast("New Round");

        playState = 0;
        userState = 0;
        andyState = 0;

        showPcState();
        showUserState();
    }

    public void handleChangeMove(){
        if(playState == 0){
            userState=(userState+1)%3;
            showUserState();
        }
    }

    public void handleExitRequest(){
        swipeExit=true;
        onBackPressed();
    }

    public void showMatchDetails(){
        showToast("Show Match Details");
    }

    public void handleMakeMove(){
        if(playState==0){
            playState = 1;
            new CountDownTimer(2000, 200) {

                public void onTick(long millisUntilFinished) {
                    loadResult();
                }

                public void onFinish() {
                    showResult();
                }
            }.start();
        }
    }

    public void loadResult(){
        andyState = (andyState + 1) % 3;
        if (andyState == 0) {
            andyMove.setImageResource(getResources().getIdentifier("rock", "drawable", getPackageName()));
        } else if (andyState == 1) {
            andyMove.setImageResource(getResources().getIdentifier("paper", "drawable", getPackageName()));
        } else if (andyState == 2) {
            andyMove.setImageResource(getResources().getIdentifier("scissors", "drawable", getPackageName()));
        }
    }

    public void showResult(){
        findPcMove();
        handleScores();
        handlePercentages();

        new CountDownTimer(3000, 500) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if(playState==1) {
                    handleNewRound();
                }
            }
        }.start();
    }

    public void findPcMove(){
        Random rand = new Random();
        andyState = rand.nextInt(100)%3;
        showPcState();
    }

    public void handleScores(){
        if(userState ==0 && andyState == 1){
            andyScore++;
            andyWinCount++;
            showToast("Andy Won");
        }
        else if(userState ==0 && andyState == 2){
            userScore++;
            userWinCount++;
            showToast("You Won");
        }
        else if(userState ==1 && andyState == 0){
            userScore++;
            userWinCount++;
            showToast("You Won");
        }
        else if(userState ==1 && andyState == 2){
            andyScore++;
            andyWinCount++;
            showToast("Andy Won");
        }
        else if(userState ==2 && andyState == 0){
            andyScore++;
            andyWinCount++;
            showToast("Andy Won");
        }
        else if(userState ==2 && andyState == 1){
            userScore++;
            userWinCount++;
            showToast("You Won");
        }
        else if(andyState==userState){
            userScore++;
            andyScore++;
            tieCount++;
            showToast("Tied");
        }

        showScores();
    }

    public void handlePercentages(){
        userPercentageVal = (userWinCount*1.00/(userWinCount+andyWinCount+tieCount))*100.00;
        andyPercentageVal = (andyWinCount*1.00/(userWinCount+andyWinCount+tieCount))*100.00;

        userPercentageVal = BigDecimal.valueOf(userPercentageVal)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();

        andyPercentageVal = BigDecimal.valueOf(andyPercentageVal)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();

        showPercentages();
    }

    public void handleNewMatch(){
        showToast("New Match");

        resetStates();
        showScores();
        showPercentages();
        handleStates();
    }

    public void showPercentages(){
        userPercentageTxt.setText(String.valueOf(userPercentageVal)+"%");
        andyPercentageTxt.setText(String.valueOf(andyPercentageVal)+"%");
    }

    public void showScores(){
        userScoreBtn.setText(Integer.toString(userScore));
        andyScoreBtn.setText(Integer.toString(andyScore));
    }

    public void resetStates(){
        playState = 0;
        userState = 0;
        andyState = 0;
        userScore = 0;
        andyScore = 0;
        userWinCount = 0;
        andyWinCount = 0;
        tieCount = 0;
        userPercentageVal = 0;
        andyPercentageVal = 0;
        tiePercentageVal = 0;
    }

    public void handleStates(){
        showPcState();
        showUserState();
    }

    public void showPcState(){
        if(andyState==0) {
            andyMove.setImageResource(getResources().getIdentifier("rock", "drawable", getPackageName()));
        }
        else if(andyState==1) {
            andyMove.setImageResource(getResources().getIdentifier("paper", "drawable", getPackageName()));
        }
        else if(andyState==2) {
            andyMove.setImageResource(getResources().getIdentifier("scissors", "drawable", getPackageName()));
        }
    }

    public void showUserState(){
        if(userState==0) {
            userMove.setImageResource(getResources().getIdentifier("rock", "drawable", getPackageName()));
        }
        else if(userState==1) {
            userMove.setImageResource(getResources().getIdentifier("paper", "drawable", getPackageName()));
        }
        else if(userState==2) {
            userMove.setImageResource(getResources().getIdentifier("scissors", "drawable", getPackageName()));
        }
    }

    public void showToast(String message){
        Toast.makeText(PlayActivity.this, message, Toast.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(PlayActivity.this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        if(swipeExit==false){
            this.doubleBackToExitPressedOnce = false;
            Intent intent = new Intent(PlayActivity.this, LaunchActivity.class);
//            intent.putExtra("btnText", "Resume");
            startActivity(intent);
            finish();
        } else if(swipeExit==true){
            showToast("Swipe Again To Exit");
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
