package com.biswash.hamrobazzar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class DashboardActivity extends AppCompatActivity {
    ViewFlipper v_flipper;
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        int image[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4};

        v_flipper = findViewById(R.id.v_flipper);

        //for loop

        for (int i = 0; i < image.length; i++){
            flipperImage(image[i]);
        }
    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000); //for 3 sec
        v_flipper.setAutoStart(true);

        //for animation

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        //v_flipper.setInAnimation(this,android.R.anim.slide_out_right);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }


}
