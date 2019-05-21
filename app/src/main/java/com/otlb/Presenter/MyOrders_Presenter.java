package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.MyOrders_Response;
import com.otlb.Model.Profile_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.GetProfile_View;
import com.otlb.View.MyOrders_View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrders_Presenter {

    MyOrders_View loginvieew;

    public MyOrders_Presenter(Context context, MyOrders_View Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void Login(String user) {
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<MyOrders_Response> call = apiInterface.GetOrders("Bearer "+user);
        call.enqueue(new Callback<MyOrders_Response>() {
            @Override
            public void onResponse(Call<MyOrders_Response> call, Response<MyOrders_Response> response) {

                if(response.code()==200){
                    loginvieew.list(response.body().getData());
                } else{
                    loginvieew.Error();
                }

            }




            @Override
            public void onFailure(Call<MyOrders_Response> call, Throwable t) {
                loginvieew.Error();
            }
        });
    }
}
