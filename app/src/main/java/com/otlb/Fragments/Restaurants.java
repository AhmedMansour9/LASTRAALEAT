package com.otlb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.otlb.Activites.Navigation;
import com.otlb.Adapter.Restaurants_Adapter;
import com.otlb.Language;
import com.otlb.Model.Offers_Details;
import com.otlb.Presenter.GetRestaurants;
import com.raaleat.R;
import com.otlb.View.RestaurantDetails_View;
import com.otlb.View.Restaurants_View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class Restaurants extends Fragment implements RestaurantDetails_View,Restaurants_View,
        SwipeRefreshLayout.OnRefreshListener{


    public Restaurants() {
        // Required empty public constructor
    }
    View view;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    Restaurants_Adapter restaurants_adapter;
    GetRestaurants getUnits_presenter;
    String Typeid,Cityid,Stateid;
    String Lang,Type,lati,lng;
    EditText E_Search;
    RelativeLayout Rela_Recycle,Rela_Filter;
    Button Filter;
    RelativeLayout Img_Rating,Img_AToZ,Img_MinOrder;
    List<com.otlb.Model.Restaurants> restaurantsList=new ArrayList<>();
    TextView NoResult,seafood;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_restaurants, container, false);
        Navigation();
        init();
        Language();
        SwipRefresh();
        RecycleviewSerach();
        Sort_AToZ();

        Sort_Rating();
        DisableRecycle();
        return view;
    }

    public void init(){
        Filter=view.findViewById(R.id.filter);
        Rela_Recycle=view.findViewById(R.id.Rela_One);
        Rela_Filter=view.findViewById(R.id.Rela_Two);
        Img_Rating=view.findViewById(R.id.Rela_Changepass);
        Img_AToZ=view.findViewById(R.id.Rela_Atoz);
        E_Search=view.findViewById(R.id.E_Search);
        recyclerView=view.findViewById(R.id.recycler_Restaurants);
        NoResult=view.findViewById(R.id.NoResult);
        seafood=view.findViewById(R.id.seafood);
        getUnits_presenter=new GetRestaurants(getContext(),this);
        Bundle bundle=getArguments();
        if(bundle!=null){
            Cityid=bundle.getString("cityid");
            Typeid=bundle.getString("typeid");
            Stateid=bundle.getString("stateid");
            Type=bundle.getString("type");
            lati=bundle.getString("lat");
            lng=bundle.getString("lng");
            seafood.setText(Type);
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);

        getUnits_presenter.GetRestaurants(Lang,Stateid,Cityid,Typeid);
    }

    @Override
    public void listRestaurants(List<com.otlb.Model.Restaurants> list) {
        restaurantsList=list;
        mSwipeRefreshLayout.setRefreshing(false);
        restaurants_adapter=new Restaurants_Adapter(restaurantsList,getContext());
        restaurants_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(restaurants_adapter);

    }

    @Override
    public void listRestaurantsOffers(List<Offers_Details> list) {

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void EmptyResult() {
        mSwipeRefreshLayout.setRefreshing(false);
        NoResult.setVisibility(View.VISIBLE);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Restaurants);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
           getUnits_presenter.GetRestaurants(Lang,Stateid,Cityid,Typeid);

            }
        });
    }
    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }
        else {
            Lang="en";
        }
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
    public void id(int postion,String id, String Image,String Name) {
        Menus detailsHomeProductFragment=new Menus();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        bundle.putString("img",Image);
        bundle.putString("name",Name);
        detailsHomeProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.Home_Frame,detailsHomeProductFragment)
                .addToBackStack(null).commit();

    }

    @Override
    public void OpenDetails(String title, String Image, String id, String Image_meal, String Name,String aa,String details) {

    }

    @Override
    public void AddtoCart(String id) {

    }

    public void RecycleviewSerach(){
        E_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                restaurants_adapter.getFilter().filter(charSequence);
                restaurants_adapter.notifyDataSetChanged();

            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    public void Sort_Rating(){
        Img_Rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rela_Recycle.setVisibility(View.VISIBLE);
                Rela_Filter.setVisibility(View.GONE);

//                Collections.sort(restaurantsList, new Comparator<com.otlb.Model.Restaurants>() {
//
//                    @Override
//                    public int compare(com.otlb.Model.Restaurants o1, com.otlb.Model.Restaurants o2) {
//                        return Double.compare(Double.parseDouble(o1.getRating()), Double.parseDouble(o2.getRating()));
//                    }
//                });
//                Collections.reverse(restaurantsList);
//                restaurants_adapter.notifyDataSetChanged();
            }
        });


    }
    public void Sort_AToZ(){
        Img_AToZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rela_Recycle.setVisibility(View.VISIBLE);
                Rela_Filter.setVisibility(View.GONE);

                Collections.sort(restaurantsList, new Comparator<com.otlb.Model.Restaurants>() {
                    @Override
                    public int compare(com.otlb.Model.Restaurants restaurants, com.otlb.Model.Restaurants t1) {
                        if(Language.isRTL()){
                            char l = Character.toUpperCase(restaurants.getName().charAt(0));
                            if (l < 'ا' || l > 'ي')
                                l += 'ي';
                            char r = Character.toUpperCase(restaurants.getName().charAt(0));
                            if (r < 'ا' || r > 'ي')
                                r += 'ي';
                            String s1 = l + restaurants.getName().substring(1);
                            String s2 = r + restaurants.getName().substring(1);
                            return s1.compareTo(s2);

                        }else {
                            char l = Character.toUpperCase(restaurants.getName().charAt(0));
                            if (l < 'A' || l > 'Z')
                                l += 'Z';
                            char r = Character.toUpperCase(restaurants.getName().charAt(0));
                            if (r < 'A' || r > 'Z')
                                r += 'Z';
                            String s1 = l + restaurants.getName().substring(1);
                            String s2 = r + restaurants.getName().substring(1);
                            return s1.compareTo(s2);

                        }
                    }
                });

                restaurants_adapter.notifyDataSetChanged();

            }
        });


    }


    public void DisableRecycle(){
        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Rela_Recycle.setVisibility(View.GONE);
            Rela_Filter.setVisibility(View.VISIBLE);


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
