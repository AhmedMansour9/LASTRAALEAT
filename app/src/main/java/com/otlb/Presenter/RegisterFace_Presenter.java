package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.RegisterFaceResponse;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.RegisterFaceView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFace_Presenter {

    RegisterFaceView loginFacevieew;

    public RegisterFace_Presenter(Context context, RegisterFaceView Loginview)
    {
        this.loginFacevieew=Loginview;

    }

    public void RegisterFace(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("name", user.getFirstName());
        queryMap.put("email", user.getEmail());
        queryMap.put("fb_id", user.getId());
        queryMap.put("api_token", "100");

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<RegisterFaceResponse> call = apiInterface.RegisterFace_Book(queryMap);
        call.enqueue(new Callback<RegisterFaceResponse>() {
            @Override
            public void onResponse(Call<RegisterFaceResponse> call, Response<RegisterFaceResponse> response) {

                if (response.isSuccessful()) {

                    loginFacevieew.openMainFace(response.body().getData().getUserToken());
                }else {
                    loginFacevieew.showErrorFace("");

                }


            }


            @Override
            public void onFailure(Call<RegisterFaceResponse> call, Throwable t) {
                loginFacevieew.showErrorFace("");

            }
        });
    }
}
