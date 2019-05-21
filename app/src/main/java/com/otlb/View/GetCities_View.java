package com.otlb.View;

import com.otlb.Model.Cities;
import com.otlb.Model.States;
import com.otlb.Model.TypesFood;

import java.util.List;

public interface GetCities_View {

    void listCities(List<Cities> list);
    void listRestaurants(List<Cities> list);
    void listStates(List<States> list);
    void listTypes(List<TypesFood> list);
   void Error(String a);
}
