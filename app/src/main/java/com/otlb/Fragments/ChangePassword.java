package com.otlb.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.ContextThemeWrapper;
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
import com.otlb.Model.UserRegister;
import com.otlb.Presenter.Change_Profile_Presenter;
import com.otlb.R;
import com.otlb.View.Change_Profile_View;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePassword extends Fragment implements Change_Profile_View {


    public ChangePassword() {
        // Required empty public constructor
    }

    Button btn_confirmpassword;
    View view;
    SharedPreferences Shared;
    String user;
    Change_Profile_Presenter change_password;
    EditText E_OldPassword,E_NewPassword;
    ProgressBar ProgrossPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_change_password, container, false);
         Navigation();
        btn_confirmpassword=view.findViewById(R.id.btn_save);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        change_password=new Change_Profile_Presenter(getContext(),this);
        E_OldPassword=view.findViewById(R.id.login_password);
        E_NewPassword=view.findViewById(R.id.login_confirmpassword);
        ProgrossPassword=view.findViewById(R.id.progressBarRegister);
        user=Shared.getString("logggin",null);
        ChangePassword();


        return view;
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
    public void ChangePassword(){
        btn_confirmpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(E_OldPassword,getResources().getString(R.string.insertoldpas));
                FUtilsValidation.isEmpty(E_NewPassword, getResources().getString(R.string.insertnewpas));


                if (!E_OldPassword.getText().toString().isEmpty()&&!E_NewPassword.getText().toString().isEmpty()) {
                    UserRegister userr=new UserRegister();
                    userr.setOldpassword(E_OldPassword.getText().toString());
                    userr.setNewpassword(E_NewPassword.getText().toString());
                    userr.setUser_token(user);
                    ProgrossPassword.setVisibility(View.VISIBLE);
                    change_password.changepass(userr);

//                    android.support.v7.app.AlertDialog.Builder alertDialog2 =
//                            new android.support.v7.app.AlertDialog.Builder(
//                                    new ContextThemeWrapper(getActivity(), android.R.style.Theme_Dialog));
//                    alertDialog2.setTitle(getResources().getString(R.string.confirmchangepassword));
//                    alertDialog2.setCancelable(false);
//                    alertDialog2.setMessage(getResources().getString(R.string.askforchange));
//
//                    alertDialog2.setPositiveButton(getResources().getString(R.string.yes),
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            });
//                    alertDialog2.setNegativeButton(getResources().getString(R.string.no),
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // Write your code here to execute after dialog
//
//                                    dialog.cancel();
//                                }
//                            });
//                    alertDialog2.show();
                }

            }
        });

    }

    @Override
    public void ProfileUpdated(ChangProfile changProfile) {

    }

    @Override
    public void PasswordUpdted() {
        Toast.makeText(getContext(), getResources().getString(R.string.passwordupdated), Toast.LENGTH_SHORT).show();
        ProgrossPassword.setVisibility(View.GONE);
    }

    @Override
    public void PasswordWrong() {
        Toast.makeText(getContext(), getResources().getString(R.string.wrongpass), Toast.LENGTH_SHORT).show();
        ProgrossPassword.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        ProgrossPassword.setVisibility(View.GONE);
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

