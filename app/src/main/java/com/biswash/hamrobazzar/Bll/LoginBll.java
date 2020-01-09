package com.biswash.hamrobazzar.Bll;

import com.biswash.hamrobazzar.api.UserAPI;
import com.biswash.hamrobazzar.serverresponse.signUpResponse;
import com.biswash.hamrobazzar.url.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {


    boolean isSuccess = false;

    public boolean checkUser(String email, String password) {

        UserAPI userAPI = URL.getInstance().create(UserAPI.class);
        Call<signUpResponse> usersCall = userAPI.checkUser(email, password);

        try {
            Response<signUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                URL.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
