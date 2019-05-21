package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.Cities_Response;
import com.otlb.Model.TypesFood;
import com.otlb.Model.TypesFoodResponse;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.GetCities_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTypes_Presenter {

    GetCities_View getCities_view;

    public GetTypes_Presenter(Context context, GetCities_View getCities_view) {
        this.getCities_view = getCities_view;

    }

    public void GetTypes(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<TypesFoodResponse> call = apiInterface.GetTypes(queryMap);
        call.enqueue(new Callback<TypesFoodResponse>() {
            @Override
            public void onResponse(Call<TypesFoodResponse> call, Response<TypesFoodResponse> response) {

                if (response.isSuccessful()) {
                    getCities_view.listTypes(response.body().getData());
                } else {
                    getCities_view.Error("");
                }
            }


            @Override
            public void onFailure(Call<TypesFoodResponse> call, Throwable t) {
                getCities_view.Error("");

            }
        });
    }
}
