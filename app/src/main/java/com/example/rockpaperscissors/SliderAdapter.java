package com.example.rockpaperscissors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    private int[] slide_images = {
            R.drawable.controller_icon,
            R.drawable.rock_icon,
            R.drawable.paper_icon,
            R.drawable.scissors_icon
    };

    public String[] slide_headings = {
        "Controls",
            "Rock",
            "Paper",
            "Scissors"
    };

    public String[] slide_details = {
            "To CHANGE MOVE: SINGLE TAP\n\nTo MAKE MOVE: SWIPE UP\n\nTo START NEW ROUND: SWIPE UP\n\nTo RESTART MATCH: SWIPE DOWN\n\nTo EXIT APP: SWIPE RIGHT TWICE",
            "If you play rock, you will be able to beat another player who has chosen scissors (\"rock crushes scissors\").\n\nBut you will lose to one who has played paper (\"paper covers rock\").",
            "If you play paper, you will be able to beat another player who has chosen rock (\"paper covers rock\").\n\nBut you will lose to one who has played scissors (\"scissors cuts paper\").",
            "If you play scissors, you will be able to beat another player who has chosen paper (\"scissors cuts paper\").\n\nBut you will lose to one who has played rock (\"rock crushes scissors\")."
    };

    public SliderAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDetails = (TextView) view.findViewById(R.id.slide_details);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDetails.setText(slide_details[position]);

        container.addView(view);

        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
