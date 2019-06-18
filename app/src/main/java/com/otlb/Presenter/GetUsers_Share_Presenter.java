package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.GetUsersShare_Response;
import com.otlb.Model.Restaurants_Response;
import com.otlb.Model.TokenResponse;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.GetUsers_Share_View;
import com.otlb.View.Restaurants_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUsers_Share_Presenter {

    GetUsers_Share_View getUsers_share_view;

    public GetUsers_Share_Presenter(Context context, GetUsers_Share_View getCities_view) {
        this.getUsers_share_view = getCities_view;

    }
    public void GetUsersShare(String user) {
        Map<String, String> queryMap = new HashMap<>();
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<GetUsersShare_Response> call = apiInterface.GetUsersShare("Bearer "+user);
        call.enqueue(new Callback<GetUsersShare_Response>() {
            @Override
            public void onResponse(Call<GetUsersShare_Response> call, Response<GetUsersShare_Response> response) {

                if (response.code()==200) {

                    getUsers_share_view.Success(response.body().getData());
//                        getService.ShowTotalprice(String.valueOf(response.body().getData().getTotalCarts()));
                }   else if(response.code()==404){
                    getUsers_share_view.Error();

                }
                else {
                    getUsers_share_view.Error();
                }
            }
            @Override
            public void onFailure(Call<GetUsersShare_Response> call, Throwable t) {
                getUsers_share_view.Error();
            }
        });
    }

}
