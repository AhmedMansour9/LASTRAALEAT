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
import com.otlb.Model.Details_Offers;
import com.otlb.Model.MenuDetails;
import com.otlb.Model.MyOrderss;
import com.raaleat.R;
import com.otlb.View.RestaurantDetails_View;

import java.util.ArrayList;
import java.util.List;

public class MyOrders_Adapter extends RecyclerView.Adapter<MyOrders_Adapter.MyViewHolder>{

    public static List<MyOrderss> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    RestaurantDetails_View restaurantDetails_view;
    List<MenuDetails> list=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView orderId,amount,amountDelivery,status,date,totalPrice,time;
        private Button Callnow,Details;
        private Button AddtoCart;
        private ImageView Image_Unit;
        private ProgressBar ProgrossSpare;
        RelativeLayout relaa;

        public MyViewHolder(View view) {
            super(view);
            orderId=view.findViewById(R.id.orderid);
            amount=view.findViewById(R.id.amount);
            amountDelivery=view.findViewById(R.id.amountDelivery);
            status=view.findViewById(R.id.status);
            date=view.findViewById(R.id.date);
            totalPrice=view.findViewById(R.id.totalPrice);
            time=view.findViewById(R.id.time);
        }
    }

    public MyOrders_Adapter(List<MyOrderss> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(RestaurantDetails_View restaurantDetails_view) {
        this.restaurantDetails_view = restaurantDetails_view;

    }
    @Override
    public MyOrders_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_myorders, parent, false);
        return new MyOrders_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyOrders_Adapter.MyViewHolder holder, final int position) {
        holder.orderId.setText(con.getResources().getString(R.string.orderid)+": "+filteredList.get(position).getOrderId());
        holder.amount.setText(con.getResources().getString(R.string.amount));
        holder.amountDelivery.setText(con.getResources().getString(R.string.amountDelivery)+": "+
                filteredList.get(position).getAmountDelivery());
        holder.status.setText(con.getResources().getString(R.string.status)+": "+filteredList.get(position).getStatus());
        holder.date.setText(con.getResources().getString(R.string.date)+": "+filteredList.get(position).getDate());
        holder.totalPrice.setText(con.getResources().getString(R.string.totalPrice)+": "+filteredList.get(position).getTotalPrice()+" "+con.getResources().getString(R.string.sar)+" "+con.getResources().getString(R.string.sar));
        holder.time.setText(con.getResources().getString(R.string.time)+": "+filteredList.get(position).getTime());

        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
        holder.orderId.setTypeface(typeface);
        holder.amount.setTypeface(typeface);
        holder.amountDelivery.setTypeface(typeface);
        holder.status.setTypeface(typeface);
        holder.date.setTypeface(typeface);
        holder.totalPrice.setTypeface(typeface);
        holder.time.setTypeface(typeface);



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

