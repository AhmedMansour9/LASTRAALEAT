package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.UserLoginResponse;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.RegistergoogleView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registergoogle {

    RegistergoogleView logingooglevieew;

    public Registergoogle(Context context, RegistergoogleView Loginview)
    {
        this.logingooglevieew=Loginview;

    }

    public void Registergoogle(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("name", user.getFirstName());
        queryMap.put("email", user.getEmail());
        queryMap.put("google_id", user.getId());
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<UserLoginResponse> call = apiInterface.RegisterGoogle(queryMap);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                if (response.isSuccessful()) {
                    logingooglevieew.openMainGoogle(response.body().getAccessToken());

                }
            }


            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                logingooglevieew.showErrorGoogle("");

            }
        });
    }
}
