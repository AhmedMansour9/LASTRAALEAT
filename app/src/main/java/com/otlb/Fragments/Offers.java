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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.otlb.Activites.Navigation;
import com.otlb.Adapter.Offers_Adapter;
import com.otlb.Adapter.Restaurants_Adapter;
import com.otlb.Language;
import com.otlb.Model.Offers_Details;
import com.otlb.Model.Restaurants;
import com.otlb.Presenter.GetRestaurants;
import com.raaleat.R;
import com.otlb.View.RestaurantDetails_View;
import com.otlb.View.Restaurants_View;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Offers extends Fragment implements RestaurantDetails_View, Restaurants_View,
        SwipeRefreshLayout.OnRefreshListener{


    public Offers() {
        // Required empty public constructor
    }
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    Offers_Adapter restaurants_adapter;
    GetRestaurants getUnits_presenter;
    String Lang;
    View view;
    TextView NoResult;
    List<Offers_Details> restaurantsList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_offers, container, false);
        Navigation();
        Language();
        SwipRefresh();


        return view;
    }
    public void Navigation(){
        NoResult=view.findViewById(R.id.NoResult);
        recyclerView=view.findViewById(R.id.recycler_Restaurants);
        getUnits_presenter=new GetRestaurants(getContext(),this);
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
    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }
        else {
            Lang="en";
        }
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
                getUnits_presenter.GetRestaurantsOffers(Lang);
            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getUnits_presenter.GetRestaurantsOffers(Lang);
    }

    @Override
    public void id(int postion,String id, String Image, String Name) {

        Details_Offers detailsHomeProductFragment=new Details_Offers();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        bundle.putString("img",Image);
        bundle.putString("name",Name);
        bundle.putString("price",Name);
        detailsHomeProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.Frame_Offer,detailsHomeProductFragment)
                .addToBackStack(null).commit();

    }

    @Override
    public void OpenDetails(String title, String Image, String id, String Image_meal, String Name, String Price,String a) {

    }

    @Override
    public void AddtoCart(String id) {

    }

    @Override
    public void listRestaurants(List<Restaurants> list) {

    }

    @Override
    public void listRestaurantsOffers(List<Offers_Details> list) {
        restaurantsList=list;
        mSwipeRefreshLayout.setRefreshing(false);
        restaurants_adapter=new Offers_Adapter(restaurantsList,getContext());
        restaurants_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(restaurants_adapter);

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
