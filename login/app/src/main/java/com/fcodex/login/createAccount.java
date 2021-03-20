package com.fcodex.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class createAccount extends AppCompatActivity {

    private TextInputEditText entername, lastname, enterpassword, enteremail;
    private MaterialButton loginbtn, signup;
    SharedPreferences sp;
    private String namestr, lastnamestr, emailstr, passwordstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        id();
        onclick();
        validation();
        sp = getSharedPreferences("myaccount", Context.MODE_PRIVATE);
    }
    private void id() {
        entername = findViewById(R.id.enterName);
        lastname = findViewById(R.id.lastName);
        enterpassword = findViewById(R.id.enterpassword);
        enteremail = findViewById(R.id.enterEmail);
        loginbtn = findViewById(R.id.loginBtn);
        signup = findViewById(R.id.signupButton);
    }
    private void onclick() {
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createAccount.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                finish();
            }
        });

        signup.setOnClickListener(v -> {
            validation();
            namestr = entername.getText().toString();
            lastnamestr = lastname.getText().toString();
            emailstr = enteremail.getText().toString();
            passwordstr = enterpassword.getText().toString();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("name", namestr);
            editor.putString("password", passwordstr);
            editor.putString("last name", lastnamestr);
            editor.putString("email", emailstr);
            editor.apply();
            Toast.makeText(createAccount.this, "Data Saved", Toast.LENGTH_SHORT);
            Intent intent = new Intent(createAccount.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        });
    }
    @SuppressLint("ShowToast")
    private void validation() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String stringLoginEmail = enteremail.getText().toString().trim();
        String stringLoginPassword = enterpassword.getText().toString().trim();
        if (!stringLoginEmail.matches(emailPattern)) {
            Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
        }
        else if ((stringLoginPassword.length() > 8)){
            Toast.makeText(this, "Passwrod contain Maximum 8 character", Toast.LENGTH_SHORT).show();
        }  else if ((stringLoginPassword.length() < 8)){
            Toast.makeText(this, "Passwrod contain Minimum 8 character", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(0, 0);
    }
}