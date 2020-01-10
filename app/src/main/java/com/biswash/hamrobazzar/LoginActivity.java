package com.biswash.hamrobazzar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.biswash.hamrobazzar.Bll.LoginBll;
import com.biswash.hamrobazzar.fragments.Homefragment;
import com.biswash.hamrobazzar.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //binding
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
       /* btnSignup = findViewById(R.id.btnRegister);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickonbuttontosignup

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

         /*     Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);*/
            }
        });
    }


    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        LoginBll loginBLL = new LoginBll();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username, password)) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etUsername.requestFocus();
        }


    }
}