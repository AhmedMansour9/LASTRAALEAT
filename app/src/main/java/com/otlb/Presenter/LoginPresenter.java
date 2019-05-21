package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.UserLoginResponse;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.LoginView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    LoginView loginvieew;

    public LoginPresenter(Context context, LoginView Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void Login(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("email", user.getEmail());
        queryMap.put("password", user.getPassword());

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<UserLoginResponse> call = apiInterface.Login(queryMap);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                    if(response.code()==200){
                        loginvieew.OpenRole(response.body().getAccessToken());
                    } else if(response.code()==401){
                        loginvieew.Invalidemail("");
                    }

                }




            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                loginvieew.showError("");

            }
        });
    }
}
