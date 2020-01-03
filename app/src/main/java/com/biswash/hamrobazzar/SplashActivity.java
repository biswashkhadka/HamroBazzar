package com.biswash.hamrobazzar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                checkUser();
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }


        }, 2000);
    }
    private void checkUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        if(username.equals("admin") &&
                password.equals("admin")) {
            Toast.makeText(SplashActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(SplashActivity.this, "Either Username or Password Wrong", Toast.LENGTH_SHORT).show();
        }
    }

}