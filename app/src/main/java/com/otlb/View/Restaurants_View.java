package com.otlb.View;

import com.otlb.Model.Offers_Details;
import com.otlb.Model.Restaurants;

import java.util.List;

public interface Restaurants_View {

    void listRestaurants(List<Restaurants> list);
    void listRestaurantsOffers(List<Offers_Details> list);
    void Error();
    void EmptyResult();
}
