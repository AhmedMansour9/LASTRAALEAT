package com.otlb.View;

public interface AddToCart_View {

    void  Result(String Message);
    void  Updated(String Message);
    void DeleteCart(String countity);
    void Success();

    void Failed();
    void Error();
}
