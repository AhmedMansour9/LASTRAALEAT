package com.otlb.View;

import com.otlb.Model.GetUsersShare;

import java.util.List;

public interface GetUsers_Share_View {

    void Success(List<GetUsersShare> list);
    void Error();
}
