package com.otlb.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.otlb.Model.Cities;
import com.otlb.Model.MenuDetails;
import com.otlb.Model.Menuu;
import com.otlb.Model.Restaurants;
import com.raaleat.R;
import com.otlb.View.RestaurantDetails_View;

import java.util.ArrayList;
import java.util.List;

public class Menus_Adapter extends RecyclerView.Adapter<Menus_Adapter.MyViewHolder>{

    private List<Menuu> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    RestaurantDetails_View restaurantDetails_view;
    String Price;
    List<Restaurants> list=new ArrayList<>();
    int row_index=0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Name;
        View views;
        RelativeLayout relative_row;
        public MyViewHolder(View view) {
            super(view);
            Name=view.findViewById(R.id.Name);
            views=view.findViewById(R.id.view);
            relative_row=view.findViewById(R.id.relative_row);


        }
    }

    public Menus_Adapter(List<Menuu> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    //    public Restaurants_Adapter(List<Units_Detail> list){
//        this.filteredList=list;
//
//    }
    public void setClickListener(RestaurantDetails_View restaurantDetails_view) {
        this.restaurantDetails_view = restaurantDetails_view;

    }

    @Override
    public Menus_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu, parent, false);
        return new Menus_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Menus_Adapter.MyViewHolder holder, final int position) {


        holder.Name.setText(filteredList.get(position).getName());
        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
        holder.Name.setTypeface(typeface);

        if(row_index==position){
            holder.views.setVisibility(View.VISIBLE);        }
        else
        {
            holder.views.setVisibility(View.GONE);
        }
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              row_index=position;
              notifyDataSetChanged();

              restaurantDetails_view.id(position,filteredList.get(position).getId(),"","");

          }
      });



    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}

