package com.otlb.View;

public interface LoginView {

    void openMain(String usertoken);
    void OpenRole( String usertoken);
    void showError(String error);
    void Invalidemail(String password);

}
