package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.AnswerShare_Response;
import com.otlb.Model.GetUsersShare_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.AcceptShare_View;
import com.otlb.View.GetUsers_Share_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnswerShare_Presenter {

    AcceptShare_View getUsers_share_view;

    public AnswerShare_Presenter(Context context, AcceptShare_View getCities_view) {
        this.getUsers_share_view = getCities_view;

    }
    public void AnswerShare(String answer,String user) {
        Map<String, String> queryMap = new HashMap<>();
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        queryMap.put("answar",answer);
        Call<AnswerShare_Response> call = apiInterface.AnswerShare(queryMap,"Bearer "+user);
        call.enqueue(new Callback<AnswerShare_Response>() {
            @Override
            public void onResponse(Call<AnswerShare_Response> call, Response<AnswerShare_Response> response) {

                if (response.code()==200) {

                    getUsers_share_view.Success();
//                        getService.ShowTotalprice(String.valueOf(response.body().getData().getTotalCarts()));
                }   else if(response.code()==404){
                    getUsers_share_view.Error();

                }
                else {
                    getUsers_share_view.Error();
                }
            }
            @Override
            public void onFailure(Call<AnswerShare_Response> call, Throwable t) {
                getUsers_share_view.Error();
            }
        });
    }


}
