package com.biswash.hamrobazzar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinAddress3;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinAddress3=findViewById(R.id.spinAddress3);
        btnRegister=findViewById(R.id.btnRegister);

        String cities[] = {"", "Kathmandu", "Lalitpur", "Bhaktapur", "Pokhara", "Bara", "Baglung", "Bandipur", "Banke", "Banepa", "Biratnagar", "Butwal",
                "Birhing", "Bhadrapur", "Bhairahawa", "Chitwan", "Damak", "Dang", "Dhading", "Dhangadi", "Dharan", "Gorkha", "Gulmi", "Hetauda", "Ilam", "Itahari", "Jnaapur",
                "Kapilvastu", "Kanchanpur", "Mahendranagr", "Nepalgunj", "Nuwakot", "Rupandehi", "Siraha", "Surkhet", "Syangja", "Tansen", "Triyuga"};
        ArrayAdapter adapter= new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_1, cities
        );
        spinAddress3.setAdapter(adapter);
    }
}
