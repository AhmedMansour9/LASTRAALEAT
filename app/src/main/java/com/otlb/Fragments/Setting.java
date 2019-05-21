package com.otlb.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.otlb.Activites.Navigation;
import com.otlb.MainActivity;
import com.otlb.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Setting extends Fragment {


    public Setting() {
        // Required empty public constructor
    }
    View view;
    SharedPreferences.Editor Sha;
   RelativeLayout Rela_Accountinfo,Rela_Changepass,Rela_language,Rela_Logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_setting, container, false);
        init();
        Navigation();

        return view;
    }

    private void init() {
        Rela_Accountinfo=view.findViewById(R.id.Rela_Accountinfo);
        Rela_Changepass=view.findViewById(R.id.Rela_Changepass);
        Rela_language=view.findViewById(R.id.Rela_language);
        Rela_Logout=view.findViewById(R.id.Rela_Logout);
        Rela_Accountinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edit_Profile detailsHomeProductFragment=new Edit_Profile();
                Bundle bundle=new Bundle();
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_Setting,detailsHomeProductFragment)
                        .addToBackStack(null).commit();


            }
        });
        Rela_Changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChangePassword detailsHomeProductFragment=new ChangePassword();
                Bundle bundle=new Bundle();
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_Setting,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });
        Rela_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Language detailsHomeProductFragment=new Language();
                Bundle bundle=new Bundle();
                detailsHomeProductFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.Rela_Setting,detailsHomeProductFragment)
                        .addToBackStack(null).commit();

            }
        });
        Rela_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sha=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
                Sha.putString("logggin",null);
                Sha.apply();
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();


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
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            Navigation.Visablty = true;
        } else {

        }

    }
}
