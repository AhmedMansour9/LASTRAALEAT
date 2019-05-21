package com.otlb.View;

public interface RestaurantDetails_View {

    void id(int postion,String id,String Image,String Name);
    void OpenDetails(String title,String Image,String id,String Image_meal,String Name,String Price,String Details);
    void AddtoCart(String id);
}
