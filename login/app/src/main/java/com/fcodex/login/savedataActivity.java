package com.fcodex.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class savedataActivity extends AppCompatActivity {

    private TextView t1,t2,t3,t4;
    private MaterialButton logout, setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedata);


        id();
        sharedpreference();
        onclick();
    }
    private void id() {
            t1 = findViewById(R.id.t1);
            t2 = findViewById(R.id.t2);
            t3 = findViewById(R.id.t3);
            t4 = findViewById(R.id.t4);
            logout = findViewById(R.id.logout);
            setting = findViewById(R.id.setting);
        }
    private void sharedpreference() {

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myaccount",Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        String lastname = sharedPreferences.getString("lastname", "");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        t1.setText(name);
        t2.setText(lastname);
        t3.setText(email);
        t4.setText(password);
    }
    private void onclick() {
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("myaccount",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Intent intent = new Intent(savedataActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(0, 0);
    }
}