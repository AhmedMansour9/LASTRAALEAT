package com.otlb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.otlb.Model.Cities;
import com.otlb.Model.Packages_Details;
import com.otlb.Model.Restaurants;
import com.otlb.R;
import com.otlb.View.PackageId_View;
import com.otlb.View.RestaurantDetails_View;

import java.util.ArrayList;
import java.util.List;

public class Packages_Adapter extends RecyclerView.Adapter<Packages_Adapter.MyViewHolder>{

    private List<Packages_Details> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    RestaurantDetails_View restaurantDetails_view;
    String Price;
    List<Restaurants> list=new ArrayList<>();
    int row_index=0;
    PackageId_View packageId_view;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView coin,Price;
        View views;
        RelativeLayout relative_row;
        Button Btn_PayCoin;
        public MyViewHolder(View view) {
            super(view);
            coin=view.findViewById(R.id.coin);
            Price=view.findViewById(R.id.Price);
//            views=view.findViewById(R.id.view);
            relative_row=view.findViewById(R.id.relative_row);
            Btn_PayCoin=view.findViewById(R.id.Btn_PayCoin);

        }
    }

    public Packages_Adapter(List<Packages_Details> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
        public void Packages(PackageId_View list){
        this.packageId_view=list;

    }
    public void setClickListener(RestaurantDetails_View restaurantDetails_view) {
        this.restaurantDetails_view = restaurantDetails_view;

    }

    @Override
    public Packages_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_packages, parent, false);
        return new Packages_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Packages_Adapter.MyViewHolder holder, final int position) {


        holder.coin.setText(filteredList.get(position).getPoint()+" "+con.getResources().getString(R.string.coins));
        holder.Price.setText(filteredList.get(position).getPrice()+" "+con.getResources().getString(R.string.sar));


//        if(row_index==position){
//            holder.relative_row.setBackground(con.getResources().getDrawable(R.drawable.bc_package));
//        }
//        else
//        {
//            holder.relative_row.setBackgroundColor(con.getResources().getColor(R.color.white));
//        }

//       holder.relative_row.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               row_index=position;
//               notifyDataSetChanged();
//
//           }
//       });
        holder.Btn_PayCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packageId_view.Package(filteredList.get(position).getId(),filteredList.get(position).getPrice());

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

