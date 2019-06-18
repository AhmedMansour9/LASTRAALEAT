package com.otlb.Fragments;


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
import com.otlb.Adapter.GetUsers_Share_Adapter;
import com.otlb.Language;
import com.otlb.Model.GetUsersShare;
import com.otlb.Model.MyOrderss;
import com.otlb.Presenter.GetUsers_Share_Presenter;
import com.otlb.View.GetUsers_Share_View;
import com.raaleat.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcceptShare extends Fragment implements SwipeRefreshLayout.OnRefreshListener, GetUsers_Share_View {


    public AcceptShare() {
        // Required empty public constructor
    }
    View view;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    GetUsers_Share_Adapter restaurants_adapter;
    GetUsers_Share_Presenter getUsers_share_presenter;
    String Lang;
    List<MyOrderss> restaurantsList = new ArrayList<>();
    SharedPreferences Shared;
    String userr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_accept_share, container, false);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        userr=Shared.getString("logggin",null);
        Navigation();
        Language();
        SwipRefresh();


        return view;
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
                mSwipeRefreshLayout.setRefreshing(true);
                getUsers_share_presenter.GetUsersShare(userr);

            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getUsers_share_presenter.GetUsersShare(userr);

    }

    public void Navigation() {
        recyclerView = view.findViewById(R.id.recycler_Restaurants);
        getUsers_share_presenter = new GetUsers_Share_Presenter(getContext(), this);
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

    @Override
    public void Success(List<GetUsersShare>list) {
        mSwipeRefreshLayout.setRefreshing(false);
        restaurants_adapter=new GetUsers_Share_Adapter(list,getContext());
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

