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
import android.widget.Filter;
import android.widget.Filterable;
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
import com.otlb.Model.Restaurants;
import com.raaleat.R;
import com.otlb.View.RestaurantDetails_View;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Restaurants_Adapter extends RecyclerView.Adapter<Restaurants_Adapter.MyViewHolder>implements Filterable {

    private List<Restaurants> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    RestaurantDetails_View restaurantDetails_view;
    private List<Restaurants> mArrayList;
    public static List<Restaurants> filtered = new ArrayList<>();
    List<Restaurants> list=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Name,Norate,statues;
        private Button Callnow,Details;
        private ImageView Image_Unit,arrow;
        private ProgressBar ProgrossSpare;
        private ImageView person_image,Starone,Startwo,StarThree,StarFour,StarFive;
        RelativeLayout relaa;

        public MyViewHolder(View view) {
            super(view);
            Name=view.findViewById(R.id.Name);
            Image_Unit=view.findViewById(R.id.img);
            arrow=view.findViewById(R.id.arrow);
            Starone=view.findViewById(R.id.Starone);
            Startwo=view.findViewById(R.id.Startwo);
            StarThree=view.findViewById(R.id.Starthree);
            StarFour=view.findViewById(R.id.StarFour);
            StarFive=view.findViewById(R.id.StarFive);
            Norate=view.findViewById(R.id.Norate);
            statues=view.findViewById(R.id.statues);
        }
    }

    public Restaurants_Adapter(List<Restaurants> list, Context context){
        this.filteredList=list;
        this.con=context;
        mArrayList=list;
    }
//    public Restaurants_Adapter(List<Units_Detail> list){
//        this.filteredList=list;
//
//    }
    public void setClickListener(RestaurantDetails_View restaurantDetails_view) {
        this.restaurantDetails_view = restaurantDetails_view;

    }

    @Override
    public Restaurants_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_restaurant, parent, false);
        return new Restaurants_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Restaurants_Adapter.MyViewHolder holder, final int position) {


        holder.Name.setText(filteredList.get(position).getName());
        holder.statues.setText(con.getResources().getString(R.string.statues)+": "+filteredList.get(position).getStatus());

        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
        holder.Name.setTypeface(typeface);
        holder.statues.setTypeface(typeface);
        holder.Norate.setTypeface(typeface);
        String i = filteredList.get(position).getLogo();
        if(i!=null) {
            Uri u = Uri.parse(i);
//            holder.ProgrossSpare.setVisibility(View.VISIBLE);
            Glide.with(con)
                    .load( u)
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
        if(filteredList.get(position).getRating().equals("No Rating")){
           holder.Norate.setVisibility(View.VISIBLE);

        }else {
            if (Integer.parseInt(filteredList.get(position).getRating()) == 1) {
                holder.Starone.setVisibility(View.VISIBLE);
            } else if (Integer.parseInt(filteredList.get(position).getRating()) == 2) {
                holder.Starone.setVisibility(View.VISIBLE);
                holder.Startwo.setVisibility(View.VISIBLE);
            } else if (Integer.parseInt(filteredList.get(position).getRating()) == 3) {
                holder.Starone.setVisibility(View.VISIBLE);
                holder.Startwo.setVisibility(View.VISIBLE);
                holder.StarThree.setVisibility(View.VISIBLE);
            } else if (Integer.parseInt(filteredList.get(position).getRating()) == 4) {
                holder.Starone.setVisibility(View.VISIBLE);
                holder.Startwo.setVisibility(View.VISIBLE);
                holder.StarThree.setVisibility(View.VISIBLE);
                holder.StarFour.setVisibility(View.VISIBLE);
            } else if (Integer.parseInt(filteredList.get(position).getRating()) == 5) {
                holder.Starone.setVisibility(View.VISIBLE);
                holder.Startwo.setVisibility(View.VISIBLE);
                holder.StarThree.setVisibility(View.VISIBLE);
                holder.StarFour.setVisibility(View.VISIBLE);
                holder.StarFive.setVisibility(View.VISIBLE);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantDetails_view.id(position,filteredList.get(position).getId()
                        ,filteredList.get(position).getImage(),filteredList.get(position).getName());
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
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                filtered.clear();
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = mArrayList;
                } else {
                    for (Restaurants androidVersion : mArrayList) {
                        if (androidVersion.getName().toLowerCase().contains(charString)) {
                            filtered.add(androidVersion);}}
                    filteredList = filtered;}
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Restaurants>) filterResults.values;

                notifyDataSetChanged();
            }
        };

    }
}


