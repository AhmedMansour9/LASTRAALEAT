package com.otlb.View;

import com.otlb.Model.Menuu;
import com.otlb.Model.TypesFood;

import java.util.List;

public interface Menu_View {

    void listTypes(List<Menuu> list);
    void Period(String period);
    void Error(String a);

}
