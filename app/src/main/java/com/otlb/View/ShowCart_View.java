package com.otlb.View;

import com.otlb.Model.CartList;
import com.otlb.Model.Cart_Details;

import java.util.List;

public interface ShowCart_View {

    void ShowCart(List<CartList> list);
    void ShowTotalprice(String price);
    void Error();
    void NoProduct();


}
