package com.fcodex.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private MaterialButton loginButton, createAccountbtn;
    private TextInputEditText loginEmail, loginPassword;
    private TextInputLayout loginEmailLayout, loginpasswordlayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    String stringLoginEmail, stringLoginPassword, email, password;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id();
        onClick();
        sharedPreference();
    }
    private void sharedPreference() {
        sharedPreferences = getSharedPreferences("myaccount", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
        password = sharedPreferences.getString("password","");
        editor = sharedPreferences.edit();
        editor.apply();
    }
    private void id() {
        loginButton = findViewById(R.id.loginButton);
        loginEmailLayout = findViewById(R.id.loginEmailLayout);
        loginpasswordlayout = findViewById(R.id.loginPasswordLayout);
        createAccountbtn = findViewById(R.id.signupButton);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
    }
    private void onClick() {
        loginButton.setOnClickListener(v -> {
            validation();
        });
        createAccountbtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,createAccount.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
    }
    private void validation() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        stringLoginEmail = Objects.requireNonNull(loginEmail.getText()).toString().trim();
        stringLoginPassword = Objects.requireNonNull(loginPassword.getText()).toString().trim();
        if (TextUtils.isEmpty(stringLoginEmail)) {
            loginEmailLayout.setError("Enter your email.");
            return;
        } else if (!stringLoginEmail.matches(emailPattern)) {
            Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(stringLoginPassword)) {
            loginpasswordlayout.setError("Enter Valid Password.");
            loginpasswordlayout.requestFocus();
        }
        else if ((stringLoginPassword.length() > 8)){
            Toast.makeText(this, "Passwrod contain Maximum 8 character", Toast.LENGTH_SHORT).show();
        }  else if ((stringLoginPassword.length() < 8)){
            Toast.makeText(this, "Passwrod contain Minimum 8 character", Toast.LENGTH_SHORT).show();
        }else {
            match();
        }
    }
    private void match(){
        if (stringLoginEmail.equals(email) && stringLoginPassword.equals(password)) {
            Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,savedataActivity.class);
            editor.putInt("flagLogin", 1);
            editor.apply();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else
            Toast.makeText(this, "Invalid Email 0r Passwrod", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(0, 0);
    }
}