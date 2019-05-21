package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.Cities_Response;
import com.otlb.Model.Details_Offers;
import com.otlb.Model.Details_Offers_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.DetailsOffers_View;
import com.otlb.View.GetCities_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details_offer_presenter {

    DetailsOffers_View getoffers;

    public Details_offer_presenter(Context context, DetailsOffers_View getCities_view) {
        this.getoffers = getCities_view;

    }

    public void GetOffers(String lang,String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("restaurantId", id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Details_Offers_Response> call = apiInterface.GetOffers(queryMap);
        call.enqueue(new Callback<Details_Offers_Response>() {
            @Override
            public void onResponse(Call<Details_Offers_Response> call, Response<Details_Offers_Response> response) {

                if (response.isSuccessful()) {
                    getoffers.offers(response.body().getData());
                } else {
                    getoffers.Error();
                }
            }


            @Override
            public void onFailure(Call<Details_Offers_Response> call, Throwable t) {
                getoffers.Error();

            }
        });
    }

}