package com.otlb.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.otlb.Activites.Navigation;
import com.otlb.Adapter.MenuDetails_Adapter;
import com.otlb.Adapter.Menus_Adapter;
import com.otlb.Adapter.Restaurants_Adapter;
import com.otlb.Language;
import com.otlb.Model.Cities;
import com.otlb.Model.MenuDetails;
import com.otlb.Model.Menuu;
import com.otlb.Model.States;
import com.otlb.Model.TypesFood;
import com.otlb.Presenter.AddToCart_Presenter;
import com.otlb.Presenter.GetMenuDetails_Presenter;
import com.otlb.Presenter.GetMenu_Presetner;
import com.otlb.R;
import com.otlb.View.AddToCart_View;
import com.otlb.View.GetCities_View;
import com.otlb.View.GetMenuDetails_View;
import com.otlb.View.Menu_View;
import com.otlb.View.RestaurantDetails_View;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menus extends Fragment implements AddToCart_View,RestaurantDetails_View, Menu_View,
        SwipeRefreshLayout.OnRefreshListener,GetMenuDetails_View{


    public Menus() {
        // Required empty public constructor
    }
    View view;
    String Id,Image,Name;
    GetMenu_Presetner getMenu_presetner;
    ImageView img;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView T_Name;
    RecyclerView recyclerView;
    RecyclerView recyclerMenudetails;
    Menus_Adapter restaurants_adapter;
    GetMenuDetails_Presenter getMenuDetails_presenter;
    String Lang;
    MenuDetails_Adapter menuDetails_adapter;
    AddToCart_Presenter addToCart_presenter;
    SharedPreferences sha;
    TextView T_Time;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_menus, container, false);
       getData();
        Language();
       Navigation();
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
                getMenu_presetner.GetMenu(Lang,Id);

            }
        });
    }

    public void getData(){
        getMenuDetails_presenter=new GetMenuDetails_Presenter(getContext(),this);
        addToCart_presenter=new AddToCart_Presenter(getContext(),this);
        recyclerMenudetails=view.findViewById(R.id.recycler_MenusDetails);
        recyclerView=view.findViewById(R.id.recycler_Menus);
        T_Name=view.findViewById(R.id.Name);
        T_Time=view.findViewById(R.id.time);
        getMenu_presetner=new GetMenu_Presetner(getContext(),this);
        img=view.findViewById(R.id.img);
        Bundle bundle=getArguments();
        if(bundle!=null){
            Id=bundle.getString("id");
            Image=bundle.getString("img");
            Name=bundle.getString("name");
            T_Name.setText(Name);
        }
        if(Image.toLowerCase().contains("http://raaleat.com/")){
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

        }else {
            Glide.with(getContext())
                    .load("http://raaleat.com/"+ Image)
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

    }



    @Override
    public void listMenus(List<MenuDetails> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        menuDetails_adapter=new MenuDetails_Adapter(list,getContext());
        menuDetails_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerMenudetails.setLayoutManager(linearLayoutManager);
        recyclerMenudetails.setItemAnimator(new DefaultItemAnimator());
        recyclerMenudetails.setAdapter(menuDetails_adapter);

    }

    @Override
    public void listTypes(List<Menuu> list) {
        getMenuDetails_presenter.GetMenuDetails(Lang,list.get(0).getId());
        mSwipeRefreshLayout.setRefreshing(false);
        restaurants_adapter=new Menus_Adapter(list,getContext());
        restaurants_adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(restaurants_adapter);

    }

    @Override
    public void Period(String period) {
        if(view!=null) {
            T_Time.setText(period + " " + view.getResources().getString(R.string.minute));
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void Error(String a) {
        mSwipeRefreshLayout.setRefreshing(false);
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
    public void onRefresh() {

       mSwipeRefreshLayout.setRefreshing(true);
        getMenu_presetner.GetMenu(Lang,Id);
    }
    public void Language(){
        if(Language.isRTL()){
            Lang="ar";
        }
        else {
            Lang="en";
        }
    }

    @Override
    public void id(int poistion,String id, String Image, String Name) {
        recyclerView.scrollToPosition(poistion);

        if(!MenuDetails_Adapter.filteredList.isEmpty()){
            MenuDetails_Adapter.filteredList.clear();
            menuDetails_adapter.notifyDataSetChanged();
        }

        mSwipeRefreshLayout.setRefreshing(true);
        getMenuDetails_presenter.GetMenuDetails("en",id);

    }

    @Override
    public void OpenDetails(String title, String Imag, String id, String Image_meal, String Names,String Price,String details) {
        Details_Meal detailsHomeProductFragment=new Details_Meal();
        Bundle bundle=new Bundle();
        bundle.putString("name",Name);
        bundle.putString("img",Image);
        bundle.putString("img_meal",Image_meal);
        bundle.putString("id",id);
        bundle.putString("name_meal",Names);
        bundle.putString("price",Price);
        bundle.putString("descrption",details);
        detailsHomeProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.Home_Frame,detailsHomeProductFragment)
                .addToBackStack(null).commit();



    }

    @Override
    public void AddtoCart(String id) {
        String login=Check_Login();
        mSwipeRefreshLayout.setRefreshing(true);
        addToCart_presenter.Addtocart(id,"1",login);
    }

    @Override
    public void Result(String Message) {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), ""+getResources().getString(R.string.addsucces), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Updated(String Message) {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), ""+getResources().getString(R.string.updatedcart), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void DeleteCart(String countity) {

    }

    @Override
    public void Success() {

    }

    @Override
    public void Failed() {

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public String Check_Login(){

        sha=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        String logi=sha.getString("logggin",null);

        return logi;
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
        view=null;
    }
}
