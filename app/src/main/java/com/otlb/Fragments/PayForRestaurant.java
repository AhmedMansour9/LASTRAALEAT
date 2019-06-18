package com.otlb.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.otlb.Activites.Navigation;
import com.otlb.Language;
import com.otlb.Model.Cities;
import com.otlb.Model.CityDetails_Spinner;
import com.otlb.Model.SpinnerAdapter;
import com.otlb.Model.States;
import com.otlb.Model.TypesFood;
import com.otlb.Model.UserRegister;
import com.otlb.Presenter.GetAllRestaurants_Presenter;
import com.otlb.Presenter.GetCities_Presenter;
import com.otlb.Presenter.PayForRestaurant_Presenter;
import com.otlb.Presenter.ShareResturant_Presenter;
import com.raaleat.R;
import com.otlb.View.GetCities_View;
import com.otlb.View.Order_View;
import com.otlb.View.ShareSuccess;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayForRestaurant extends Fragment implements Order_View,GetCities_View , ShareSuccess {


    public PayForRestaurant() {
        // Required empty public constructor
    }
    View view;
   EditText Phone;
   Button btn_Share,btn_Pay;
   ImageView PlusEmail;
   LinearLayout linearLayout;
    EditText e,E_Amount;
    GetAllRestaurants_Presenter getCities_presenter;
    Spinner Type_Spinner;
    List<CityDetails_Spinner> list_Cities=new ArrayList<>();
    String City,City_Id;
    String Lang;
    PayForRestaurant_Presenter payForRestaurant_presenter;
    ProgressBar progressBarRegister;
    String user;
    SharedPreferences Shared;
    SpinnerAdapter list_Cities_Adapter;
    ShareResturant_Presenter shareResturant_presenter;
    String y = "";
    String phones;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_pay_for_restaurant, container, false);
        Language();
        init();
        Navigation();
        CheckEmail();
        PlusEditText();

    btn_Pay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FUtilsValidation.isEmpty(E_Amount, getResources().getString(R.string.insertemail));
            if (!E_Amount.getText().toString().equals("") && City_Id!=null) {
                btn_Pay.setEnabled(false);
                progressBarRegister.setVisibility(View.VISIBLE);
                payForRestaurant_presenter.PayRestaurant(user,City_Id,E_Amount.getText().toString());
            }

        }
    });

        btn_Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View childView = getLayoutInflater().inflate(R.layout.custom_layout, null);
                e = childView.findViewById(R.id.custom_email);
                LinearLayout linearr=childView.findViewById(R.id.linearr);
                ArrayList<String> edittextArray = new ArrayList<String>();
                for(int i = 0; i < linearLayout.getChildCount(); i++){
                    View newLayout = linearLayout.getChildAt(i);
                    EditText editText =
                            newLayout.findViewById(R.id. custom_email);
                    edittextArray.add(editText.getText().toString());
                }
                for (int i=0;i<edittextArray.size();i++){

                    int a = edittextArray.size() - 1;

                    if (a == i) {
                        y = y + edittextArray.get(i);
                    } else {
                        y = y + edittextArray.get(i) + " ";
                    }
                }

                if (!E_Amount.getText().toString().equals("") && City_Id!=null&&!Phone.getText().toString().equals("")) {
                        phones=Phone.getText().toString()+" "+y;

                    btn_Share.setEnabled(false);
                    progressBarRegister.setVisibility(View.VISIBLE);
                    shareResturant_presenter.PayRestaurant(user,City_Id,E_Amount.getText().toString(),phones);
                }

            }
        });
        return view;
    }
    private void PlusEditText() {
        PlusEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.setVisibility(View.VISIBLE);
                View childView = getLayoutInflater().inflate(R.layout.custom_layout, null);
                linearLayout.addView(childView);
            }
        });

    }

    public void init(){
        shareResturant_presenter=new ShareResturant_Presenter(getContext(),this);
        Phone=view.findViewById(R.id.login_email);
        btn_Share=view.findViewById(R.id.btn_Share);
        btn_Pay=view.findViewById(R.id.btn_Pay);
        PlusEmail=view.findViewById(R.id.PlusEmail);
        linearLayout=view.findViewById(R.id.child_linear);
        Type_Spinner=view.findViewById(R.id.Type_Spinner);
        list_Cities_Adapter = new SpinnerAdapter(getActivity(),list_Cities);
        E_Amount=view.findViewById(R.id.E_Amount);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);
        progressBarRegister=view.findViewById(R.id.progressBarRegister);
        getCities_presenter=new GetAllRestaurants_Presenter(getContext(),this);
        payForRestaurant_presenter=new PayForRestaurant_Presenter(getContext(),this);
        getCities_presenter.GetCities(Lang);
        Bundle bundle=getArguments();
        if(bundle.getString("pay").equals("pay")){
            linearLayout.setVisibility(View.GONE);
            btn_Pay.setVisibility(View.VISIBLE);
            btn_Share.setVisibility(View.GONE);
            Phone.setVisibility(View.GONE);
        }else if(bundle.getString("pay").equals("share") ){
            linearLayout.setVisibility(View.VISIBLE);
            btn_Pay.setVisibility(View.GONE);
            btn_Share.setVisibility(View.VISIBLE);
        }
    }

    public void CheckEmail() {
        Phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
             if(s.toString().length()!=0){
                 btn_Share.setEnabled(true);
                 PlusEmail.setVisibility(View.VISIBLE);
             }else {
                 btn_Share.setEnabled(false);
                 PlusEmail.setVisibility(View.GONE);
             }

            }
        });

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

    @Override
    public void listCities(List<Cities> list) {

    }

    @Override
    public void listRestaurants(List<Cities> list) {
        list_Cities.clear();
        CityDetails_Spinner car_detail=new CityDetails_Spinner();
        car_detail.setId("0");
        car_detail.setName(view.getResources().getString(R.string.selectrestaurant));
        list_Cities.add(car_detail);
        for(int i=0;i<list.size();i++){
            CityDetails_Spinner car_details=new CityDetails_Spinner();
            car_details.setId(String.valueOf(list.get(i).getId()));
            car_details.setName(String.valueOf(list.get(i).getName()));
            list_Cities.add(car_details);
        }
//        list_Cities_Adapter = new ArrayAdapter<CityDetails_Spinner>(getApplicationContext(), R.layout.textcolorspinner,list_Cities) {
//            @Override
//            public View getDropDownView(int position, View convertView, ViewGroup parent) {
//                TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
//                textView.setTextColor(Color.BLACK);
//                return textView;
//            }
//        };
//        list_Cities_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Type_Spinner.setAdapter(list_Cities_Adapter);
        Type_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                City= Type_Spinner.getSelectedItem().toString();


                if(!City.equals("Select Restaurant")&&!City.equals("اختار المطعم")) {
                    for(i = 0; i<list_Cities.size(); i++){
                        if(list_Cities.get(i).getName().equals(City)){
                            City_Id=String.valueOf(list_Cities.get(i).getId());
                        }
                    }

                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void listStates(List<States> list) {

    }

    @Override
    public void listTypes(List<TypesFood> list) {

    }


    @Override
    public void SuccessShare() {
        btn_Share.setEnabled(true);
        Toast.makeText(getContext(), ""+getResources().getString(R.string.waitingresponse), Toast.LENGTH_SHORT).show();
        WaitingReplies detailsHomeProductFragment=new WaitingReplies();
        Bundle bundle=new Bundle();
        detailsHomeProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.Rela_Wallet,detailsHomeProductFragment)
                .addToBackStack(null).commit();



    }

    @Override
    public void Error(String a) {
        progressBarRegister.setVisibility(View.GONE);
        btn_Share.setEnabled(true);
    }
    public void Language (){
        if(Language.isRTL()){
            Lang="ar";
        }else {
            Lang="en";
        }

    }

    @Override
    public void OderSuccess(String orderid) {
        Toast.makeText(getContext(), ""+getResources().getString(R.string.paymentsuccess), Toast.LENGTH_SHORT).show();
        progressBarRegister.setVisibility(View.GONE);
        btn_Pay.setEnabled(true);
    }

    @Override
    public void InvalidAmount(String orderid) {
        Toast.makeText(getContext(), ""+getResources().getString(R.string.pricebiggerthanyourwallet), Toast.LENGTH_SHORT).show();
        progressBarRegister.setVisibility(View.GONE);
        btn_Pay.setEnabled(true);
    }

    @Override
    public void Error() {
        progressBarRegister.setVisibility(View.GONE);
        btn_Pay.setEnabled(true);
        btn_Share.setEnabled(true);
    }


}
