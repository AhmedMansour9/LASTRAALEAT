package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.Cities_Response;
import com.otlb.Model.Offers_Response;
import com.otlb.Model.Restaurants_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.GetCities_View;
import com.otlb.View.Restaurants_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRestaurants {

    Restaurants_View getCities_view;

    public GetRestaurants(Context context, Restaurants_View getCities_view) {
        this.getCities_view = getCities_view;

    }

    public void GetRestaurants(String lang,String Stateid,String City_id,String Type_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("city_id", City_id);
        queryMap.put("state_id", Stateid);
        if(Type_id!=null) {
            queryMap.put("type_id", Type_id);
        }

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Restaurants_Response> call = apiInterface.GetRestaurants(queryMap);
        call.enqueue(new Callback<Restaurants_Response>() {
            @Override
            public void onResponse(Call<Restaurants_Response> call, Response<Restaurants_Response> response) {

                if (response.code()==200) {
                    getCities_view.listRestaurants(response.body().getData());
                } else if(response.code()==404){
                    getCities_view.EmptyResult();
                }else {
                    getCities_view.Error();
                }
            }


            @Override
            public void onFailure(Call<Restaurants_Response> call, Throwable t) {
                getCities_view.Error();

            }
        });
    }
    public void GetRestaurantsOffers(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Offers_Response> call = apiInterface.GetRestaurantsOffers(queryMap);
        call.enqueue(new Callback<Offers_Response>() {
            @Override
            public void onResponse(Call<Offers_Response> call, Response<Offers_Response> response) {

                if (response.isSuccessful()) {
                    getCities_view.listRestaurantsOffers(response.body().getData());
                } else {
                    getCities_view.Error();
                }
            }


            @Override
            public void onFailure(Call<Offers_Response> call, Throwable t) {
                getCities_view.Error();

            }
        });
    }
    public void GetNearestRestaurants(String lang,String lati,String lng) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("lat", lati);
        queryMap.put("lng", lng);


        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<Restaurants_Response> call = apiInterface.GetRestaurants(queryMap);
        call.enqueue(new Callback<Restaurants_Response>() {
            @Override
            public void onResponse(Call<Restaurants_Response> call, Response<Restaurants_Response> response) {

                if (response.code()==200) {
                    getCities_view.listRestaurants(response.body().getData());
                } else if(response.code()==404){
                    getCities_view.EmptyResult();
                }else {
                    getCities_view.Error();
                }
            }


            @Override
            public void onFailure(Call<Restaurants_Response> call, Throwable t) {
                getCities_view.Error();

            }
        });
    }

}
