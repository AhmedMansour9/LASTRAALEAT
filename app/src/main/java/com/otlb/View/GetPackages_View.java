package com.otlb.View;

import com.otlb.Model.Packages_Details;

import java.util.List;

public interface GetPackages_View {

    void listpackages(List<Packages_Details>list);
    void Error();
}
