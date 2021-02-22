package com.example.instaex01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#1a1b29"))
//                .withHeaderText("Instagram")
//                .withFooterText("Footer")
                .withBeforeLogoText("Instagram")
//                .withAfterLogoText("After Logo Text")
                .withLogo(R.drawable.instagram_logo);

        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextSize(40);
        config.getLogo().setScaleType(ImageView.ScaleType.CENTER_CROP);
        config.getLogo().setMaxWidth(200);
        config.getLogo().setMaxHeight(200);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}