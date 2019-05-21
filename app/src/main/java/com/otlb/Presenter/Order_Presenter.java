package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.ChangePassword_Response;
import com.otlb.Model.Order_Response;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.Change_Profile_View;
import com.otlb.View.Order_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_Presenter {

    Order_View order_view;

    public Order_Presenter(Context context, Order_View Loginview) {
        this.order_view = Loginview;

    }

    public void Order(String user,String Address,String lat,String lng,String type) {

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("address", Address);
        queryMap.put("lat", lat);
        queryMap.put("lng", lng);
        queryMap.put("payment_type", type);
        Call<Order_Response> call = apiInterface.Order( queryMap,"Bearer " + user);
        call.enqueue(new Callback<Order_Response>() {
            @Override
            public void onResponse(Call<Order_Response> call, Response<Order_Response> response) {

                if (response.code() == 200) {

                    order_view.OderSuccess(String.valueOf(response.body().getData().getOrderId()));

                } else if(response.code()==400){
                    order_view.InvalidAmount("");
                }
                else {
                    order_view.Error();
                }


            }

            @Override
            public void onFailure(Call<Order_Response> call, Throwable t) {
                order_view.Error();

            }
        });
    }

}