package com.otlb.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.otlb.Activites.Navigation;
import com.otlb.Model.ChangProfile;
import com.otlb.Model.Profile;
import com.otlb.Model.UserRegister;
import com.otlb.Presenter.Change_Profile_Presenter;
import com.otlb.Presenter.GetProfile_Presenter;
import com.raaleat.R;
import com.otlb.TextDrawable;
import com.otlb.View.Change_Profile_View;
import com.otlb.View.GetProfile_View;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Edit_Profile extends Fragment implements GetProfile_View , Change_Profile_View {


    public Edit_Profile() {
        // Required empty public constructor
    }
    EditText E_Name,E_Email,login_Address,login_email,T_ModileNumber;
    Change_Profile_Presenter change_password;
    ProgressBar progrossprofileedit;
    SharedPreferences Shared;
    View view;
    Button confirm;
    String userr;
   GetProfile_Presenter getProfile_presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_edit__profile, container, false);
         init();
        Navigation();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(E_Name,getResources().getString(R.string.insertfirstname));
                FUtilsValidation.isEmpty(login_Address,getResources().getString(R.string.insertAddress));
                FUtilsValidation.isEmpty(login_email,getResources().getString(R.string.insertemail));
                FUtilsValidation.isEmpty(T_ModileNumber,getResources().getString(R.string.insertphone));
                UserRegister user=new UserRegister();
                user.setFirstName(E_Name.getText().toString());
                user.setPhone(T_ModileNumber.getText().toString());
                user.setAddress(login_Address.getText().toString());
                user.setEmail(login_email.getText().toString());
                user.setUser_token(userr);
                progrossprofileedit.setVisibility(View.VISIBLE);
                change_password.changeuser(user);
            }
        });
        return view;
    }
    public void init(){
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        progrossprofileedit=view.findViewById(R.id.progressBarRegister);
        getProfile_presenter=new GetProfile_Presenter(getContext(),this);
        change_password=new Change_Profile_Presenter(getContext(),this);
        userr=Shared.getString("logggin",null);
        getProfile_presenter.Login(userr);
        progrossprofileedit.setVisibility(View.VISIBLE);
        E_Name=view.findViewById(R.id.login_name);
        login_Address=view.findViewById(R.id.login_Address);
        login_email=view.findViewById(R.id.login_email);
        T_ModileNumber=view.findViewById(R.id.login_phone);
        String code = "+966";
        T_ModileNumber.setCompoundDrawablesWithIntrinsicBounds(new TextDrawable(code), null, null, null);
        T_ModileNumber.setCompoundDrawablePadding(code.length()*10);

        confirm=view.findViewById(R.id.btn_save);

    }

    @Override
    public void profile(Profile profile) {
        progrossprofileedit.setVisibility(View.GONE);
        E_Name.setText(profile.getName());
        login_Address.setText(profile.getAddress());
        login_email.setText(profile.getEmail());
        T_ModileNumber.setText(profile.getPhone());
    }

    @Override
    public void Error() {
        progrossprofileedit.setVisibility(View.GONE);
    }

    @Override
    public void ProfileUpdated(ChangProfile changProfile) {
        progrossprofileedit.setVisibility(View.GONE);
        Toast.makeText(getContext(), ""+getResources().getString(R.string.profileupdated), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void PasswordUpdted() {

    }

    @Override
    public void PasswordWrong() {

    }

    @Override
    public void showError() {
        progrossprofileedit.setVisibility(View.GONE);
    }
    public void Navigation(){
        Toolbar toolbar =view.findViewById(R.id.toolbarrestaurants);

        Navigation.toggle = new ActionBarDrawerToggle(
                getActivity(), Navigation.drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        Navigation.drawer.addDrawerListener(Navigation.toggle);
        Navigation.toggle.syncState();

        Navigation.toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable. navigation);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Navigation.drawer.isDrawerOpen(GravityCompat.START)) {
                    Navigation.drawer.closeDrawer(GravityCompat.START);
                } else {
                    Navigation.drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty=false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Navigation.Visablty=true;
    }
}
