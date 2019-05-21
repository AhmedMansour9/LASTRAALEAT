package com.otlb.Adapter;

import android.content.Context;
import android.graphics.Paint;
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
import com.otlb.Model.Details_Offers;
import com.otlb.Model.MenuDetails;
import com.otlb.Model.Offers_Details;
import com.otlb.Model.Restaurants;
import com.otlb.R;
import com.otlb.View.RestaurantDetails_View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailsOffers_Adapter extends RecyclerView.Adapter<DetailsOffers_Adapter.MyViewHolder>{

    public static List<Details_Offers> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    RestaurantDetails_View restaurantDetails_view;
    List<MenuDetails> list=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Name,Price,T_OfferPrice,From,To;
        private Button Callnow,Details;
        private Button AddtoCart;
        private ImageView Image_Unit;
        private ProgressBar ProgrossSpare;
        RelativeLayout relaa;

        public MyViewHolder(View view) {
            super(view);
            Name=view.findViewById(R.id.Name);
            Price=view.findViewById(R.id.Price);
            Image_Unit=view.findViewById(R.id.img);
            AddtoCart=view.findViewById(R.id.addtoCart);
            T_OfferPrice=view.findViewById(R.id.OfferPrice);
            From=view.findViewById(R.id.datefrom);
            To=view.findViewById(R.id.dateto);
        }
    }

    public DetailsOffers_Adapter(List<Details_Offers> list, Context context){
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
    public DetailsOffers_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_offerdetails, parent, false);
        return new DetailsOffers_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DetailsOffers_Adapter.MyViewHolder holder, final int position) {


        holder.Name.setText(filteredList.get(position).getMenuDetailsName());
        holder.From.setText(con.getResources().getString(R.string.datefrom)+": "+filteredList.get(position).getFromDate());
        holder.To.setText(con.getResources().getString(R.string.dateto)+": "+filteredList.get(position).getToDate());
        double value = Double.parseDouble(filteredList.get(position).getOriginalPrice());
        value =Double.parseDouble(new DecimalFormat("##.####").format(value));
        holder.Price.setText(value+" "+con.getResources().getString(R.string.sar));

        double value1 = Double.parseDouble(filteredList.get(position).getOfferPrice());
        value1 =Double.parseDouble(new DecimalFormat("##.####").format(value));

        holder.T_OfferPrice.setText(value1+" "+con.getResources().getString(R.string.sar));
        holder.Price.setPaintFlags(holder.Price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
        holder.Name.setTypeface(typeface);
        holder.From.setTypeface(typeface);
        holder.To.setTypeface(typeface);
        holder.Price.setTypeface(typeface);
        holder.T_OfferPrice.setTypeface(typeface);
        holder.Price.setTypeface(typeface);
        String i = filteredList.get(position).getMenuDetailsImage();
        if(i!=null) {
            Uri u = Uri.parse(i);
//            holder.ProgrossSpare.setVisibility(View.VISIBLE);
            Glide.with(con)
                    .load(u)
                    .apply(new RequestOptions().override(500, 500))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            holder.ProgrossSpare.setVisibility(View.GONE);
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            holder.ProgrossSpare.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.Image_Unit);

        }

        holder.AddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restaurantDetails_view.OpenDetails("","",filteredList.get(position).getMenuDetailsId()
                        ,filteredList.get(position).getMenuDetailsImage()
                        ,filteredList.get(position).getMenuDetailsName()
                        ,filteredList.get(position).getOfferPrice(),"");

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                restaurantDetails_view.OpenDetails("","",filteredList.get(position).getMenuDetailsId()
                        ,filteredList.get(position).getMenuDetailsImage(),
                        filteredList.get(position).getMenuDetailsName()
                        ,filteredList.get(position).getOfferPrice(),"");
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

