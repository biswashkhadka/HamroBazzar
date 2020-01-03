package com.biswash.hamrobazzar.TermsUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.biswash.hamrobazzar.R;
import com.biswash.hamrobazzar.WelcomeActivity;

public class AdRulesActivity extends AppCompatActivity {
    TextView tvRules;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_rules);

        tvRules= findViewById(R.id.tvRules);
        btnBack= findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdRulesActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
