package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.Cities_Response;
import com.otlb.Model.MenuDetails_Response;
import com.otlb.Model.Menu_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.GetCities_View;
import com.otlb.View.Menu_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMenu_Presetner {

    Menu_View getCities_view;

    public GetMenu_Presetner(Context context, Menu_View getCities_view) {
        this.getCities_view = getCities_view;

    }

    public void GetMenu(String lang,String Restaurant) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("restaurant_id", Restaurant);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Menu_Response> call = apiInterface.GetMenu(queryMap);
        call.enqueue(new Callback<Menu_Response>() {
            @Override
            public void onResponse(Call<Menu_Response> call, Response<Menu_Response> response) {

                if (response.isSuccessful()) {
                    getCities_view.listTypes(response.body().getData().getData());
                    getCities_view.Period(response.body().getData().getPeriod());
                } else {
                    getCities_view.Error("");
                }
            }


            @Override
            public void onFailure(Call<Menu_Response> call, Throwable t) {
                getCities_view.Error("");

            }
        });
    }
}
