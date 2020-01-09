package com.biswash.hamrobazzar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.biswash.hamrobazzar.api.UserAPI;
import com.biswash.hamrobazzar.model.Users;
import com.biswash.hamrobazzar.serverresponse.ImageResponse;
import com.biswash.hamrobazzar.serverresponse.signUpResponse;
import com.biswash.hamrobazzar.strictmode.StrictModeClass;
import com.biswash.hamrobazzar.url.URL;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private CircleImageView imgProfile;
    private EditText etFullname, etEmail, etPasswordR, etConfirmPass,etPhone,etMobile, etAddress1,etAddress2,etAddress3;
    private Button btnSignup;
    String imagePath;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //  spinAddress3=findViewById(R.id.spinAddress3);
        btnSignup= findViewById(R.id.btnSignupR);
        imgProfile = findViewById(R.id.imgProfile);
        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail);
        etPasswordR = findViewById(R.id.etPasswordR);
        etConfirmPass= findViewById(R.id.etConfirmPass);
        etPhone = findViewById(R.id.etPhone);
        etMobile = findViewById(R.id.etMobile);
        etAddress1 = findViewById(R.id.etAddress1);
        etAddress2 = findViewById(R.id.etAddress2);
        etAddress3 = findViewById(R.id.etAddress3);


     /*   String cities[] = {"", "Kathmandu", "Lalitpur", "Bhaktapur", "Pokhara", "Bara", "Baglung", "Bandipur", "Banke", "Banepa", "Biratnagar", "Butwal",
        "Birhing", "Bhadrapur", "Bhairahawa", "Chitwan", "Damak", "Dang", "Dhading", "Dhangadi", "Dharan", "Gorkha", "Gulmi", "Hetauda", "Ilam", "Itahari", "Jnaapur",
        "Kapilvastu", "Kanchanpur", "Mahendranagr", "Nepalgunj", "Nuwakot", "Rupandehi", "Siraha", "Surkhet", "Syangja", "Tansen", "Triyuga"};
        ArrayAdapter adapter= new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_1, cities
        );
        spinAddress3.setAdapter(adapter);
*/

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPasswordR.getText().toString().equals(etConfirmPass.getText().toString())) {
                    if(validate()) {
                        saveImageOnly();
                        signUp();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etPasswordR.requestFocus();
                    return;
                }

            }
        });
    }

    private boolean validate() {
        boolean status=true;
        if (etEmail.getText().toString().length() < 6) {
            etEmail.setError("error");
            status=false;
        }
        return status;
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UserAPI usersAPI = URL.getInstance().create(UserAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void signUp() {


        String fullname = etFullname.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPasswordR.getText().toString();
        String phone = etPhone.getText().toString();
        String mobilephone = etMobile.getText().toString();
        String address1 = etAddress1.getText().toString();
        String address2 = etAddress2.getText().toString();
        String address3 = etAddress3.getText().toString();

        Users users = new Users( fullname,email, password, phone,mobilephone,address1,address2,address3,imageName);

        UserAPI usersAPI = URL.getInstance().create(UserAPI.class);
        Call<signUpResponse> signUpCall = usersAPI.registerUser(users);

        signUpCall.enqueue(new Callback<signUpResponse>() {
            @Override
            public void onResponse(Call<signUpResponse> call, Response<signUpResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<signUpResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
