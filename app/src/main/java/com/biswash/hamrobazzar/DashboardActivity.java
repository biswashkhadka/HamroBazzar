package com.biswash.hamrobazzar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.biswash.hamrobazzar.api.UserAPI;
import com.biswash.hamrobazzar.model.ListedAds;
import com.biswash.hamrobazzar.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    ImageView Profile;
    ViewFlipper v_flipper;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Profile = findViewById(R.id.profile);


        recyclerView=findViewById(R.id.recyclerView);

        //instance for interface
        UserAPI usersAPI = URL.getInstance().create(UserAPI.class);
        Call<List<ListedAds>> listCall=usersAPI.getListedAds();



        //asynchronous
        listCall.enqueue(new Callback<List<ListedAds>>() {
            @Override
            public void onResponse(Call<List<ListedAds>> call, Response<List<ListedAds>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DashboardActivity.this, "Error code"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("msg","onFailure"+ response.code());

                    return;
                }

                List<ListedAds> listedAdsList = response.body();

                ListedAdsAdapter listedAdsAdapter = new ListedAdsAdapter(DashboardActivity.this,listedAdsList);
                recyclerView.setAdapter(listedAdsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this,LinearLayoutManager.HORIZONTAL,false));



            }


            @Override
            public void onFailure(Call<List<ListedAds>> call, Throwable t) {
                Log.d("msg","onFailure"+t.getLocalizedMessage());
                Toast.makeText(DashboardActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        int image[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4};

        v_flipper = findViewById(R.id.v_flipper);

        //for loop

        for (int i = 0; i < image.length; i++){
            flipperImage(image[i]);
        }
    }

    private void openDialog() {
        logindialogActivity loginDialogActivity = new logindialogActivity();
        loginDialogActivity.show(getSupportFragmentManager(), "login dialog");
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
