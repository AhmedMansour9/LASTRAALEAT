package com.otlb.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otlb.Adapter.MyOrders_Adapter;
import com.otlb.Model.MyOrderss;
import com.otlb.Presenter.MyOrders_Presenter;
import com.raaleat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsOrders extends Fragment {


    public DetailsOrders() {
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
        view= inflater.inflate(R.layout.fragment_details_orders, container, false);


        return view;
    }

}
