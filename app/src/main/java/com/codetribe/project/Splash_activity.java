package com.codetribe.project;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_activity extends AppCompatActivity {

    private ImageView splashImg;
    private Animation fade_in;
    private static int SPLASH_TIME_OUT=4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        splashImg=(ImageView)findViewById(R.id.splashImg);

        animate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(Splash_activity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    public void animate()
    {

        fade_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        splashImg.startAnimation(fade_in);

    }
}
