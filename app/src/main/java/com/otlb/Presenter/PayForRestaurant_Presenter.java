package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.Order_Response;
import com.otlb.Model.PayForRestaurant_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.Order_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayForRestaurant_Presenter {

    Order_View order_view;

    public PayForRestaurant_Presenter(Context context, Order_View Loginview) {
        this.order_view = Loginview;

    }

    public void PayRestaurant(String user, String Restaurantid, String amount) {

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("restaurant_id", Restaurantid);
        queryMap.put("amount", amount);

        Call<PayForRestaurant_Response> call = apiInterface.PayForRestaurant(queryMap, "Bearer " + user);
        call.enqueue(new Callback<PayForRestaurant_Response>() {
            @Override
            public void onResponse(Call<PayForRestaurant_Response> call, Response<PayForRestaurant_Response> response) {

                if (response.code() == 200) {

                    order_view.OderSuccess("");

                } else if(response.code()==400){
                    order_view.InvalidAmount("");
                }else {
                    order_view.Error();
                }


            }

            @Override
            public void onFailure(Call<PayForRestaurant_Response> call, Throwable t) {
                order_view.Error();

            }
        });
    }

}

