package com.biswash.hamrobazzar.TermsUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.biswash.hamrobazzar.R;
import com.biswash.hamrobazzar.WelcomeActivity;

public class TermsofUseActivity extends AppCompatActivity {
    TextView tvTerms;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsof_use);

        tvTerms= findViewById(R.id.tvTerms);
        btnBack= findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsofUseActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
