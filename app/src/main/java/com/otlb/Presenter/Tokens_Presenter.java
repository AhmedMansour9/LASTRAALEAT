package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.CartList;
import com.otlb.Model.CartResponse;
import com.otlb.Model.TokenResponse;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.ShowCart_View;
import com.otlb.View.Tokens_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tokens_Presenter {

    Tokens_View getService;

    public Tokens_Presenter(Context context, Tokens_View getService)
    {
        this.getService=getService;

    }

    public void UpdateToken(String token,String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("token_app", token);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<TokenResponse> call = apiInterface.UpDateToken(queryMap,"Bearer "+user);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                if (response.code()==200) {

                    getService.success();
//                        getService.ShowTotalprice(String.valueOf(response.body().getData().getTotalCarts()));
                }   else if(response.code()==404){
                    getService.Error();

                }
                else {
                    getService.Error();
                }
            }
            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                getService.Error();
            }
        });
    }

}
