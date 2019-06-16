package com.otlb.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.otlb.Model.PayForRestaurant_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.Order_View;
import com.otlb.View.ShareSuccess;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareResturant_Presenter {

    ShareSuccess order_view;
    Context context;

    public ShareResturant_Presenter(Context contexts, ShareSuccess Loginview) {
        this.order_view = Loginview;
    context=contexts;
    }

    public void PayRestaurant(String user, String Restaurantid, String amount,String phone) {

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("restaurant_id", Restaurantid);
        queryMap.put("amount", amount);
        queryMap.put("phone", phone);

        Call<PayForRestaurant_Response> call = apiInterface.ShareGroub(queryMap, "Bearer " + user);
        call.enqueue(new Callback<PayForRestaurant_Response>() {
            @Override
            public void onResponse(Call<PayForRestaurant_Response> call, Response<PayForRestaurant_Response> response) {

                if (response.code() == 200) {

                    order_view.SuccessShare();

                } else if(response.code()==404){
//                    order_view.InvalidAmount("");
//                }else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String Error=jObjError.getString("error");
                        order_view.Error(Error);
                    } catch (Exception e) {
//                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<PayForRestaurant_Response> call, Throwable t) {
                order_view.Error("");

            }
        });
    }

}

