package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.RegisterResponse;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.RegisterView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Presenter {


    RegisterView registerView;

    public Register_Presenter(Context context, RegisterView registerView) {
        this.registerView = registerView;

    }

    public void register(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_token", "100");
        queryMap.put("firstName", user.getFirstName());
        queryMap.put("lastName","null");
        queryMap.put("password", user.getPassword());
        queryMap.put("email", user.getEmail());
        queryMap.put("phone", user.getPhone());
        if(user.getAddress()!=null){
        queryMap.put("address",user.getAddress());
        }

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<RegisterResponse> call = apiInterface.register(queryMap);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.code()==200) {
                    registerView.openMain();
                } else if(response.code()==400){
                    registerView.EmailisUsed();
                }else {
                    registerView.showError("");
                }
            }
            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerView.showError("");

            }
        });
    }

}