package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.Cities_Response;
import com.otlb.Model.RegisterResponse;
import com.otlb.Model.StatesResponse;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.GetCities_View;
import com.otlb.View.RegisterView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCities_Presenter {

    GetCities_View getCities_view;

    public GetCities_Presenter(Context context, GetCities_View getCities_view) {
        this.getCities_view = getCities_view;

    }

    public void GetCities(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Cities_Response> call = apiInterface.GetCities(queryMap);
        call.enqueue(new Callback<Cities_Response>() {
            @Override
            public void onResponse(Call<Cities_Response> call, Response<Cities_Response> response) {

                if (response.isSuccessful()) {
                    getCities_view.listCities(response.body().getData());
                } else {
                    getCities_view.Error("");
                }
            }


            @Override
            public void onFailure(Call<Cities_Response> call, Throwable t) {
                getCities_view.Error("");

            }
        });
    }

    public void GetStates(String lang,String City_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("city_id", City_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<StatesResponse> call = apiInterface.GetStates(queryMap);
        call.enqueue(new Callback<StatesResponse>() {
            @Override
            public void onResponse(Call<StatesResponse> call, Response<StatesResponse> response) {

                if (response.isSuccessful()) {
                    getCities_view.listStates(response.body().getData());
                } else {
                    getCities_view.Error("");
                }
            }


            @Override
            public void onFailure(Call<StatesResponse> call, Throwable t) {
                getCities_view.Error("");

            }
        });
    }
}
