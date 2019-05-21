package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.CartList;
import com.otlb.Model.CartResponse;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.ShowCart_View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCart_Presenter {

    ShowCart_View getService;

    public ShowCart_Presenter(Context context, ShowCart_View getService)
    {
        this.getService=getService;

    }

    public void ShowCart(String lang,String user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartResponse> call = apiInterface.ShowCart(queryMap,"Bearer "+user);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                if (response.code()==200) {

                    getService.ShowCart((List<CartList>) response.body().getData());
//                        getService.ShowTotalprice(String.valueOf(response.body().getData().getTotalCarts()));
                }   else if(response.code()==404){
                        getService.NoProduct();

                }
                else {
                    getService.Error();
                }
            }
            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                getService.Error();
            }
        });
    }

}
