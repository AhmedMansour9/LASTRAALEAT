package com.otlb.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.otlb.Activites.Navigation;
import com.otlb.Presenter.MyWallet_Presenter;
import com.raaleat.R;
import com.otlb.View.MyWallet_View;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Wallet extends Fragment implements MyWallet_View {


    public Wallet() {
        // Required empty public constructor
    }
   View view;
    CardView img_addpackge;
    CardView card_paywithraaleat,card_shareiwthfriend;
    MyWallet_Presenter myWallet_presenter;
    TextView T_MyWalltet;
    String userr;
    SharedPreferences Shared;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_wallet, container, false);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        userr=Shared.getString("logggin",null);
        T_MyWalltet=view.findViewById(R.id.T_MyWalltet);
        card_paywithraaleat=view.findViewById(R.id.card_paywithraaleat);
        card_shareiwthfriend=view.findViewById(R.id.card_shareiwthfriend);
        img_addpackge=view.findViewById(R.id.card_newcoins);
        myWallet_presenter=new MyWallet_Presenter(getContext(),this);
        myWallet_presenter.Login(userr);
        Navigation();
        OpenPackageDetails();
        card_paywithraaleat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayForRestaurant detailsHomeProductFragment=new PayForRestaurant();
                Bundle bundle=new Bundle();
                bundle.putString("pay","pay");
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_Wallet,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });
        card_shareiwthfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PayForRestaurant detailsHomeProductFragment=new PayForRestaurant();
                Bundle bundle=new Bundle();
                bundle.putString("pay","share");
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_Wallet,detailsHomeProductFragment)
                        .addToBackStack(null).commit();
            }
        });

        return view;
    }

    public void OpenPackageDetails(){
        img_addpackge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WalletPackges detailsHomeProductFragment=new WalletPackges();
                Bundle bundle=new Bundle();
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_Wallet,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

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
        Navigation.Visablty=true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void coins(String coin) {
        T_MyWalltet.setText(coin);
    }

    @Override
    public void Error() {

    }
    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            Navigation.Visablty = true;
        } else {

        }

    }
}
