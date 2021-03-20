package com.fcodex.login;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class splash extends AppCompatActivity {

    private ProgressBar splashProgress;
    private final int SPLASH_TIME = 3000; //This is 3 seconds
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        id();
        playProgress();
        progressMethod();
        sharedPreferences = getSharedPreferences("myaccount", Context.MODE_PRIVATE);
    }

    private void progressMethod() {
        //Code to start timer and take action after the timer ends
        new Handler().postDelayed(() -> {
            //Do any action here. Now we are moving to next page
                int flagLogin = sharedPreferences.getInt("flagLogin", 0);
                if (flagLogin == 0) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    //This 'finish()' is for exiting the app when back button pressed from HomeFragment page which is ActivityHome
                    finish();
                } else if (flagLogin == 1) {
                    Intent intent = new Intent(this, savedataActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    //This 'finish()' is for exiting the app when back button pressed from HomeFragment page which is ActivityHome
                    finish();
                }
        }, SPLASH_TIME);
    }

    private void playProgress() {
        ObjectAnimator.ofInt(splashProgress, "Progress", 100)
                .setDuration(SPLASH_TIME)
                .start();
    }
    private void id() {
        splashProgress = findViewById(R.id.splashProgress);
    }
}