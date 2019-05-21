package com.otlb.Fragments;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.otlb.Activites.Navigation;
import com.otlb.Adapter.DetailsOffers_Adapter;
import com.otlb.Adapter.Offers_Adapter;
import com.otlb.Language;
import com.otlb.Presenter.AddToCart_Presenter;
import com.otlb.Presenter.Details_offer_presenter;
import com.otlb.Presenter.GetMenuDetails_Presenter;
import com.otlb.Presenter.GetMenu_Presetner;
import com.otlb.Presenter.GetRestaurants;
import com.otlb.R;
import com.otlb.View.DetailsOffers_View;
import com.otlb.View.RestaurantDetails_View;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Offers extends Fragment implements RestaurantDetails_View,SwipeRefreshLayout.OnRefreshListener,DetailsOffers_View {


    public Details_Offers() {
        // Required empty public constructor
    }

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    DetailsOffers_Adapter restaurants_adapter;
    Details_offer_presenter getUnits_presenter;
    String Lang;
    View view;
    String Id,Image,Name;
    ImageView img;
    TextView T_Name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_details__offers, container, false);
        Navigation();
        getData();
        Language();
        SwipRefresh();

        return view;
    }
    public void getData(){
        recyclerView=view.findViewById(R.id.recycler_Restaurants);
        T_Name=view.findViewById(R.id.Name);
        img=view.findViewById(R.id.img);
        Bundle bundle=getArguments();
        if(bundle!=null){
            Id=bundle.getString("id");
            Image=bundle.getString("img");
            Name=bundle.getString("name");
            T_Name.setText(Name);
        }

            Glide.with(getContext())
                    .load( Image)
                    .apply(new RequestOptions().override(500, 500))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(img);


    }

    @Override
    public void offers(List<com.otlb.Model.Details_Offers> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        restaurants_adapter=new DetailsOffers_Adapter(list,getContext());
        restaurants_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(restaurants_adapter);

    }

    @Override
    public void Error() {

    }

    public void Navigation(){
        recyclerView=view.findViewById(R.id.recycler_Restaurants);
        Bundle bundle=getArguments();
        if(bundle!=null){
            Id=bundle.getString("id");
        }

        getUnits_presenter=new Details_offer_presenter(getContext(),this);
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
                getUnits_presenter.GetOffers(Lang,Id);

            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getUnits_presenter.GetOffers(Lang,Id);

    }

    @Override
    public void id(int postion,String id, String Image, String Name) {

    }

    @Override
    public void OpenDetails(String title, String Ima, String id, String Image_meal, String Names, String Price,String a) {
        Details_Meal detailsHomeProductFragment=new Details_Meal();
        Bundle bundle=new Bundle();
        bundle.putString("name",Name);
        bundle.putString("img",Image);
        bundle.putString("img_meal",Image_meal);
        bundle.putString("id",id);
        bundle.putString("name_meal",Names);
        bundle.putString("price",Price);
        detailsHomeProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.Frame_Offer,detailsHomeProductFragment)
                .addToBackStack(null).commit();

    }

    @Override
    public void AddtoCart(String id) {

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
