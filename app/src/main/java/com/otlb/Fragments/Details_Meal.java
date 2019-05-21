package com.otlb.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.otlb.Presenter.AddToCart_Presenter;
import com.otlb.Presenter.GetMenuDetails_Presenter;
import com.otlb.Presenter.GetMenu_Presetner;
import com.otlb.R;
import com.otlb.View.AddToCart_View;

import java.text.DecimalFormat;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Meal extends Fragment implements AddToCart_View {


    public Details_Meal() {
        // Required empty public constructor
    }
    View view;
    String Id,Image,Name,img_meal,name_meal,Pricee,Details;
    TextView T_Name,Name_Meal,T_Price,T_qount,Descrption;
    ImageView img,Img_Meal,plus,minus;
    int poistion;
    SwipeRefreshLayout mSwipeRefreshLayout;
    AddToCart_Presenter addToCart_presenter;
    SharedPreferences sha;
     Button AddCart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_details__meal, container, false);
         getData();
        SwipRefresh();
         Plus();
         Minus();
         AddToCart();
         Navigation();


        return view;
    }

    public void getData(){
        AddCart=view.findViewById(R.id.addtoCart);
        addToCart_presenter=new AddToCart_Presenter(getContext(),this);
        T_Name=view.findViewById(R.id.Name);
        img=view.findViewById(R.id.img);
        Descrption=view.findViewById(R.id.Descrption);
        Img_Meal=view.findViewById(R.id.img_meal);
        Name_Meal=view.findViewById(R.id.Name_meal);
        T_Price=view.findViewById(R.id.Price);
        plus=view.findViewById(R.id.plus);
        T_qount=view.findViewById(R.id.T_qount);
        minus=view.findViewById(R.id.minus);
        Bundle bundle=getArguments();
        if(bundle!=null){
            Id=bundle.getString("id");
            Image=bundle.getString("img");
            Name=bundle.getString("name");
            name_meal=bundle.getString("name_meal");
            img_meal=bundle.getString("img_meal");
            Pricee=bundle.getString("price");
            Details=bundle.getString("descrption");
            Name_Meal.setText(name_meal);
            T_Name.setText(Name);
            double value = Double.parseDouble(Pricee);
            value =Double.parseDouble(new DecimalFormat("##.####").format(value));
            T_Price.setText(value+" "+getResources().getString(R.string.sar));
            Descrption.setText(Details);
        }
       if(Image.toLowerCase().contains("http://raaleat.com/")) {
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
                   .load("http://raaleat.com/"+Image)
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
      if(img_meal.toLowerCase().contains("http://raaleat.com/")) {
          Glide.with(getContext())
                  .load(img_meal)
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
                  .into(Img_Meal);
      }else {
          Glide.with(getContext())
                  .load("http://raaleat.com/" + img_meal)
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
                  .into(Img_Meal);
      }
    }
    public void Plus(){
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 poistion=Integer.parseInt(T_qount.getText().toString());
                poistion++;
                T_qount.setText(String.valueOf(poistion));

            }
        });

    }
    public void Minus(){
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poistion=Integer.parseInt(T_qount.getText().toString());
                if(poistion>1) {
                    poistion--;
                    T_qount.setText(String.valueOf(poistion));
                }

            }
        });

    }

    @Override
    public void Result(String Message) {
        AddCart.setEnabled(true);
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), ""+getResources().getString(R.string.addsucces), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(),Navigation.class));
        getActivity().finish();

    }

    @Override
    public void Updated(String Message) {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getContext(), ""+getResources().getString(R.string.updatedcart), Toast.LENGTH_SHORT).show();
        AddCart.setEnabled(true);
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
    public  void AddToCart(){
        AddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeRefreshLayout.setRefreshing(true);
                String login=Check_Login();
                AddCart.setEnabled(false);
                addToCart_presenter.Addtocart(Id,T_qount.getText().toString(),login);
            }
        });

    }

    public String Check_Login(){

        sha=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        String logi=sha.getString("logggin",null);

        return logi;
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_Restaurants);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
//        mSwipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                getUnits_presenter.GetRestaurants(Lang,Stateid,Cityid,Typeid);
//
//            }
//        });
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
        Navigation.Visablty=false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Navigation.Visablty=true;
    }

}
