package com.otlb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.otlb.Model.UserRegister;
import com.otlb.Presenter.Register_Presenter;
import com.otlb.R;
import com.otlb.TextDrawable;
import com.otlb.View.RegisterView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements RegisterView {


    public Register() {
        // Required empty public constructor
    }

    View view;
    Button Sign_Up;
    EditText E_FirstName, E_LastName, E_Emai, E_Phone, E_Password, E_CarModel, E_CarYear,login_Address,login_Confirmpassword;
    ProgressBar Progrossregister;
    //    NetworikConntection checkgbsAndNetwork;
    Register_Presenter register;
   EditText E_Amount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        register = new Register_Presenter(getContext(), this);

        E_FirstName = view.findViewById(R.id.login_name);
        Progrossregister = view.findViewById(R.id.progressBarRegister);
        E_Emai = view.findViewById(R.id.login_email);
        E_Phone = view.findViewById(R.id.login_phone);

        login_Confirmpassword=view.findViewById(R.id.login_Confirmpassword);
        String code = "+966";
        E_Phone.setCompoundDrawablesWithIntrinsicBounds(new TextDrawable(code), null, null, null);
        E_Phone.setCompoundDrawablePadding(code.length()*10);
        E_Password = view.findViewById(R.id.login_password);
        Sign_Up = view.findViewById(R.id.btn_siginin);
        login_Address=view.findViewById(R.id.login_Address);
//        checkgbsAndNetwork = new NetworikConntection(getActivity());

        Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FUtilsValidation.isEmpty(E_Emai, getResources().getString(R.string.insertemail));
                    FUtilsValidation.isEmpty(E_FirstName, getResources().getString(R.string.insertfirstname));
                    FUtilsValidation.isEmpty(E_Phone, getResources().getString(R.string.insertphone));
                    FUtilsValidation.isEmpty(E_Password, getResources().getString(R.string.insertpassword));
                    FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16);

                    if (!FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16)) {
                        E_Password.setError(getResources()
                                .getString(R.string.minpas));
                    }
                    else if (!FUtilsValidation.isLengthCorrect(login_Confirmpassword.getText().toString(), 8, 16)){
                        Toast.makeText(getActivity(), getResources()
                                .getString(R.string.minpas), Toast.LENGTH_SHORT).show();
                        login_Confirmpassword.setError(getResources()
                                .getString(R.string.minpas));
                    }
                if (!E_Password.getText().toString().equals(login_Confirmpassword.getText().toString())) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.notmatch),
                            Toast.LENGTH_SHORT).show();
                }
                else if(ValidateEmail()) {
                    if (!E_Emai.getText().toString().equals("") &&
                            !E_FirstName.getText().toString().equals("") && !E_Phone.getText().toString().equals("") &&
                            (FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16))) {
                        UserRegister user = new UserRegister();
                        user.setEmail(E_Emai.getText().toString());
                        user.setFirstName(E_FirstName.getText().toString());
                        user.setPhone(E_Phone.getText().toString());
                        user.setPassword(E_Password.getText().toString());
                        user.setAddress(login_Address.getText().toString());

                        Progrossregister.setVisibility(View.VISIBLE);
                        register.register(user);

                    }
                }
//                }
//                else {
//                    Toast.makeText(getActivity(), getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
//                }

            }
        });


        return view;
    }



    @Override
    public void openMain() {
        Toast.makeText(getActivity(), getResources().getString(R.string.Successfull), Toast.LENGTH_SHORT).show();
        Progrossregister.setVisibility(View.GONE);
        getFragmentManager().beginTransaction().replace(R.id.flContent, new Login()).commit();
    }
    private Boolean ValidateEmail(){
        String EMAIL=E_Emai.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
//            Toast.makeText(getContext(), ""+getResources().getString(R.string.insertemail), Toast.LENGTH_SHORT).show();
            FUtilsValidation.isEmpty(E_Emai, getResources().getString(R.string.insertemail));
            return false;
        }else if(!E_Emai.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            FUtilsValidation.isEmpty(E_Emai, getResources().getString(R.string.insertemail));
//            Toast.makeText(getContext(), ""+getResources().getString(R.string.insertemail), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public void EmailisUsed() {
        Toast.makeText(getActivity(), getResources().getString(R.string.emailfailed), Toast.LENGTH_SHORT).show();
        Progrossregister.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Progrossregister.setVisibility(View.GONE);
    }
}