package com.otlb.Fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.otlb.Activites.Navigation;
import com.otlb.Activites.Order_Success;
import com.otlb.Activites.PayMent;
import com.otlb.Language;
import com.otlb.Model.Cities;
import com.otlb.Model.CityDetails_Spinner;
import com.otlb.Model.Order_Response;
import com.otlb.Model.States;
import com.otlb.Model.TypesFood;
import com.otlb.Presenter.GetCities_Presenter;
import com.otlb.Presenter.GetTypes_Presenter;
import com.otlb.Presenter.Order_Presenter;
import com.otlb.R;
import com.otlb.View.GetCities_View;
import com.otlb.View.Order_View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderCart extends Fragment  implements Order_View,OnMapReadyCallback ,com.google.android.gms.location.LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    public OrderCart() {
        // Required empty public constructor
    }
    View view;
    String Lang;
    public static double latitude,longitude=0;
    GoogleApiClient mGoogleApiClient;
    LocationRequest locationReques;
    private GoogleMap googleMap;
    int REQUEST_LOCATION_CODE=99;
    List<Address> addresses;
    String addres,Cities;
    EditText ET_address,E_City;
    Button assignLocationBtn,orderBtn;
    Order_Presenter order_presenter;
    String user;
    SharedPreferences Shared;
    ProgressBar progross_order;
    String Price;
    RadioGroup radioGroup;
    String Raio;
    RadioButton radioButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_order_cart, container, false);
        Navigation();
        Language();
        initMap();
        checkLocationPermission();
        GetLocation();


        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(ET_address, getResources().getString(R.string.insertAddress));
                if(radioGroup.getCheckedRadioButtonId()!=-1){
                    int id= radioGroup.getCheckedRadioButtonId();
                    View radioButton = radioGroup.findViewById(id);
                    int radioId = radioGroup.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
                    String selection = (String) btn.getText();
                    if(!ET_address.getText().toString().equals("")) {
                        if(selection.equals("Debit/Credit Card")||selection.equals("بطاقة الخصم / الائتمان")){
                            Intent intent=new Intent(getActivity(),PayMent.class);
                            intent.putExtra("price",Price);
                            startActivity(intent);

                        }else if(selection.equals("Cash")||selection.equals("نقدي")){
                            progross_order.setVisibility(View.VISIBLE);
                            orderBtn.setEnabled(false);
                            order_presenter.Order(user,ET_address.getText().toString(),
                                    String.valueOf(latitude),String.valueOf(longitude),"cash");


                        }
                        else if(selection.equals("Wallet")||selection.equals("المحفظة")) {
                            progross_order.setVisibility(View.VISIBLE);
                            orderBtn.setEnabled(false);
                            order_presenter.Order(user, ET_address.getText().toString(),
                                    String.valueOf(latitude), String.valueOf(longitude), "wallet");

                        }
                    }



                    }
            }
        });


        return view;
    }
    public void initMap() {
        radioGroup=view.findViewById(R.id.radioSex);



        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);
        progross_order=view.findViewById(R.id.progross_order);
        ET_address=view.findViewById(R.id.order_Address);
//        E_City=view.findViewById(R.id.order_city);
        assignLocationBtn=view.findViewById(R.id.getlocation);
        orderBtn=view.findViewById(R.id.order);
        order_presenter=new Order_Presenter(getContext(),this);
        Bundle bundle=getArguments();
        if(bundle!=null){
            Price=bundle.getString("price");
        }

    }

    public void GetLocation(){

        assignLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildGoogleapiclint();
                checkLocationPermission();
            }

        });
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }
    private synchronized void buildGoogleapiclint() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    public void Navigation(){
        Toolbar toolbar =view.findViewById(R.id.toolbarhome);

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



    public void Language (){
        if(Language.isRTL()){
            Lang="ar";
        }else {
            Lang="en";
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        buildGoogleapiclint();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        locationReques.setFastestInterval(10000);

        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationReques);


            SettingsClient client = LocationServices.getSettingsClient(getActivity());
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

            task.addOnFailureListener(getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(getActivity(),
                                    REQUEST_LOCATION_CODE);
                        } catch (IntentSender.SendIntentException sendEx) {
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        try {


            Geocoder geocoder = new Geocoder(getContext());
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            addres = addresses.get(0).getAddressLine(0);
            Cities = addresses.get(0).getAdminArea();
            ET_address.setText(addres + "");
//            E_City.setText(Cities);

        } catch (IOException d) {
            d.printStackTrace();
        }
    }

    @Override
    public void OderSuccess(String Orderid) {
        progross_order.setVisibility(View.GONE);
        orderBtn.setEnabled(true);

        Intent intent=new Intent(getContext(), Order_Success.class);
        intent.putExtra("id",Orderid);
        startActivity(intent);

    }

    @Override
    public void InvalidAmount(String orderid) {
        Toast.makeText(getContext(), ""+getResources().getString(R.string.pricebiggerthanyourwallet), Toast.LENGTH_SHORT).show();
        progross_order.setVisibility(View.GONE);
        orderBtn.setEnabled(true);


    }

    @Override
    public void Error() {
        progross_order.setVisibility(View.GONE);
        orderBtn.setEnabled(true);


    }
}
