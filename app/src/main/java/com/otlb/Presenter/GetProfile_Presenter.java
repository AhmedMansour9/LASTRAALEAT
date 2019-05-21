package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.Profile_Response;
import com.otlb.Model.UserLoginResponse;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.GetProfile_View;
import com.otlb.View.LoginView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProfile_Presenter {

    GetProfile_View loginvieew;

    public GetProfile_Presenter(Context context, GetProfile_View Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void Login(String user) {
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<Profile_Response> call = apiInterface.GetProfile("Bearer "+user);
        call.enqueue(new Callback<Profile_Response>() {
            @Override
            public void onResponse(Call<Profile_Response> call, Response<Profile_Response> response) {

                if(response.code()==200){
                    loginvieew.profile(response.body().getData());
                } else{
                    loginvieew.Error();
                }

            }




            @Override
            public void onFailure(Call<Profile_Response> call, Throwable t) {
                loginvieew.Error();
            }
        });
    }
}
