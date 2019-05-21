package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.MyOrders_Response;
import com.otlb.Model.MyWallet_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.MyOrders_View;
import com.otlb.View.MyWallet_View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyWallet_Presenter {


    MyWallet_View loginvieew;

    public MyWallet_Presenter(Context context, MyWallet_View Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void Login(String user) {
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<MyWallet_Response> call = apiInterface.GetWallet("Bearer "+user);
        call.enqueue(new Callback<MyWallet_Response>() {
            @Override
            public void onResponse(Call<MyWallet_Response> call, Response<MyWallet_Response> response) {

                if(response.code()==200){
                    loginvieew.coins(response.body().getData().getAmount());
                } else{
                    loginvieew.Error();
                }

            }




            @Override
            public void onFailure(Call<MyWallet_Response> call, Throwable t) {
                loginvieew.Error();
            }
        });
    }
}
