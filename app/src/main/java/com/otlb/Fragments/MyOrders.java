package com.otlb.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
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

import com.otlb.Activites.Navigation;
import com.otlb.Adapter.MyOrders_Adapter;
import com.otlb.Adapter.Offers_Adapter;
import com.otlb.Language;
import com.otlb.Model.MyOrderss;
import com.otlb.Model.Offers_Details;
import com.otlb.Presenter.GetRestaurants;
import com.otlb.Presenter.MyOrders_Presenter;
import com.otlb.R;
import com.otlb.View.MyOrders_View;
import com.otlb.View.RestaurantDetails_View;
import com.otlb.View.Restaurants_View;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrders extends Fragment  implements MyOrders_View,
        SwipeRefreshLayout.OnRefreshListener {


    public MyOrders() {
        // Required empty public constructor
    }

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    MyOrders_Adapter restaurants_adapter;
    MyOrders_Presenter getUnits_presenter;
    String Lang;
    View view;
    List<MyOrderss> restaurantsList = new ArrayList<>();
    SharedPreferences Shared;
    String userr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_orders, container, false);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        userr=Shared.getString("logggin",null);
        Navigation();
        Language();
        SwipRefresh();


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Navigation.Visablty = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Navigation.Visablty = false;
    }

    public void Navigation() {
        recyclerView = view.findViewById(R.id.recycler_Restaurants);
        getUnits_presenter = new MyOrders_Presenter(getContext(), this);
        Toolbar toolbar = view.findViewById(R.id.toolbarrestaurants);

        Navigation.toggle = new ActionBarDrawerToggle(
                getActivity(), Navigation.drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        Navigation.drawer.addDrawerListener(Navigation.toggle);
        Navigation.toggle.syncState();

        Navigation.toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationIcon(R.drawable.navigation);

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

    public void Language() {
        if (Language.isRTL()) {
            Lang = "ar";
        } else {
            Lang = "en";
        }
    }

    public void SwipRefresh() {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_Restaurants);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getUnits_presenter.Login(userr);

            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getUnits_presenter.Login(userr);
    }


    @Override
    public void list(List<MyOrderss> list) {
        restaurantsList=list;
        mSwipeRefreshLayout.setRefreshing(false);
        restaurants_adapter=new MyOrders_Adapter(restaurantsList,getContext());
//        restaurants_adapter.setClickListener(this);
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
}
