package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView splashIcon1;
    private ImageView splashIcon2;
    private ImageView splashIcon3;

    private TextView splashTxt1;
    private TextView splashTxt2;
    private TextView splashTxt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashIcon1 = (ImageView) findViewById(R.id.splashIcon1);
        splashIcon2 = (ImageView) findViewById(R.id.splashIcon2);
        splashIcon3 = (ImageView) findViewById(R.id.splashIcon3);

        splashTxt1 = (TextView) findViewById(R.id.splashTxt1);
        splashTxt2 = (TextView) findViewById(R.id.splashTxt2);
        splashTxt3 = (TextView) findViewById(R.id.splashTxt3);

        Thread thread = new Thread(){
            @Override
            public void run() {
                doWork();
                startApp();
            }
        };

        thread.start();
    }

    public void startApp(){
        Intent intent = new Intent(SplashScreenActivity.this, LaunchActivity.class);
        startActivity(intent);
        finish();
    }

    public void doWork(){
        for(int i= 0;i<7;i++) {
            try {
                Thread.sleep(500);
                if(i%3==0) {
                    splashIcon1.setImageResource(getResources().getIdentifier("rock_icon", "drawable", getPackageName()));
                    splashIcon2.setImageResource(getResources().getIdentifier("paper_icon", "drawable", getPackageName()));
                    splashIcon3.setImageResource(getResources().getIdentifier("scissors_icon", "drawable", getPackageName()));

//                    splashTxt1.setText(getString(R.string.rock_text));
//                    splashTxt2.setText(getString(R.string.paper_text));
//                    splashTxt3.setText(getString(R.string.scissors_text));
                }
                else if(i%3==1) {
                    splashIcon1.setImageResource(getResources().getIdentifier("paper_icon", "drawable", getPackageName()));
                    splashIcon2.setImageResource(getResources().getIdentifier("scissors_icon", "drawable", getPackageName()));
                    splashIcon3.setImageResource(getResources().getIdentifier("rock_icon", "drawable", getPackageName()));

//                    splashTxt1.setText(getString(R.string.paper_text));
//                    splashTxt2.setText(getString(R.string.scissors_text));
//                    splashTxt3.setText(getString(R.string.rock_text));
                }
                else if(i%3==2) {
                    splashIcon1.setImageResource(getResources().getIdentifier("scissors_icon", "drawable", getPackageName()));
                    splashIcon2.setImageResource(getResources().getIdentifier("rock_icon", "drawable", getPackageName()));
                    splashIcon3.setImageResource(getResources().getIdentifier("paper_icon", "drawable", getPackageName()));

//                    splashTxt1.setText(getString(R.string.scissors_text));
//                    splashTxt2.setText(getString(R.string.rock_text));
//                    splashTxt3.setText(getString(R.string.paper_text));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
