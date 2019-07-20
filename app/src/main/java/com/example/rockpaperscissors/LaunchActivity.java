package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout linearLayout;
    private Button playBtn;

    private TextView[] dots;

    private boolean doubleBackToExitPressedOnce = false;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        playBtn = (Button) findViewById(R.id.playBtn);

//        message = getIntent().getStringExtra("btnText");
//        if(message!=null){
//            playBtn.setText(message);
//        }

        SliderAdapter sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaunchActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void addDotsIndicator (int position){
        dots = new TextView[4];
        linearLayout.removeAllViews();

        for(int i = 0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            if(i==position && dots.length>0){
                dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            else {
                dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            }

            linearLayout.addView(dots[i]);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void showToast(String message){
        Toast.makeText(LaunchActivity.this, message, Toast.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(LaunchActivity.this, message, Toast.LENGTH_SHORT);
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

        showToast("Press Once Again to EXIT");

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
