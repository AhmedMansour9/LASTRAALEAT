package com.otlb.View;

import com.otlb.Model.Cities;
import com.otlb.Model.MenuDetails;

import java.util.List;

public interface GetMenuDetails_View {

    void listMenus(List<MenuDetails> list);
    void Error(String a);

}
