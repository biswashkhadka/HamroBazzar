package com.biswash.hamrobazzar.api;

import com.biswash.hamrobazzar.model.ListedAds;
import com.biswash.hamrobazzar.model.Users;
import com.biswash.hamrobazzar.serverresponse.ImageResponse;
import com.biswash.hamrobazzar.serverresponse.signUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAPI {


    @POST("users/signup")
    Call<signUpResponse> registerUser(@Body Users users);

    @FormUrlEncoded
    @POST("users/login")
    Call<signUpResponse> checkUser(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<Users> getUserDetails(@Header("Authorization")String token);

    @GET("listedads")
    Call<List<ListedAds>>getListedAds();

}
