package com.otlb.View;

public interface Order_View {
    void OderSuccess(String orderid);
    void InvalidAmount(String orderid);
    void Error();
}
