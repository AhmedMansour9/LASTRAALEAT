package com.otlb.View;

import com.otlb.Model.ChangProfile;

public interface Change_Profile_View {
    void ProfileUpdated(ChangProfile changProfile);
    void PasswordUpdted();
    void PasswordWrong();
    void showError();

}
