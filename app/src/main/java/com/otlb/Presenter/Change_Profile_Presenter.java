package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.ChangePassword_Response;
import com.otlb.Model.ChangeProfile_Response;
import com.otlb.Model.UserLoginResponse;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.Change_Profile_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Change_Profile_Presenter {

    Change_Profile_View loginvieew;

    public Change_Profile_Presenter(Context context, Change_Profile_View Loginview)
    {
        this.loginvieew=Loginview;

    }

    public void changepass(UserRegister user) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("old_password", user.getOldpassword());
        queryMap.put("new_password", user.getNewpassword());
        queryMap.put("confirm_password", user.getNewpassword());


        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);


        Call<ChangePassword_Response> call = apiInterface.Changepass(queryMap,"Bearer "+user.getUser_token());
        call.enqueue(new Callback<ChangePassword_Response>() {
            @Override
            public void onResponse(Call<ChangePassword_Response> call, Response<ChangePassword_Response> response) {

                if (response.code()==200) {

                        loginvieew.PasswordUpdted();

                    }else  if (response.code()==400){
                        loginvieew.PasswordWrong();
                    }else {
                    loginvieew.showError();
                }


            }

            @Override
            public void onFailure(Call<ChangePassword_Response> call, Throwable t) {
                loginvieew.showError();

            }
        });
    }

    public void changeuser(UserRegister user){

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("phone", user.getPhone());
        queryMap.put("firstName", user.getFirstName());
        queryMap.put("lastName","alii");
        queryMap.put("email", user.getEmail());
        queryMap.put("address", user.getAddress());

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<ChangeProfile_Response> call = apiInterface.ChangeProfile(queryMap,"Bearer "+user.getUser_token());
        call.enqueue(new Callback<ChangeProfile_Response>() {
            @Override
            public void onResponse(Call<ChangeProfile_Response> call, Response<ChangeProfile_Response> response) {
                 if(response.code()==200){
                        loginvieew.ProfileUpdated(response.body().getData());
                }else {

                 loginvieew.showError();

            }

            }

            @Override
            public void onFailure(Call<ChangeProfile_Response> call, Throwable t) {
                loginvieew.showError();

            }
        });

}
}
