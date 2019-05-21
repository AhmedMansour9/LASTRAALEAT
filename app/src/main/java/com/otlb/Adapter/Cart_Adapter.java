package com.otlb.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.otlb.Model.Cart;
import com.otlb.Model.CartList;
import com.otlb.Model.Cart_Details;
import com.otlb.R;
import com.otlb.View.Count_View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {



public static List<CartList> filteredList=new ArrayList<>();
        SharedPreferences.Editor share;
public static String TotalPrice;
        View itemView;
        Context con;
        Count_View count_view;
public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView T_Name,T_Discrption,T_Model,T_Price,counter;
    ImageView Image_product;
    ProgressBar progressBar;
    ImageView btncart;
    public ImageView delete,plus,minus;
    public MyViewHolder(View view) {
        super(view);
        T_Name = view.findViewById(R.id.T_Name);
        Image_product = view.findViewById(R.id.Image_product);
        plus = view.findViewById(R.id.plus);
        T_Price = view.findViewById(R.id.T_Price);
        minus=view.findViewById(R.id.minus);
        progressBar=view.findViewById(R.id.progressBar);
        counter=view.findViewById(R.id.counter);
        delete=view.findViewById(R.id.delete);


    }


}

    public Cart_Adapter(List<CartList> phon, Context context){
        filteredList=phon;
        this.con=context;
    }

    public void count(Count_View count_view){
        this.count_view=count_view;
    }
    @Override
    public Cart_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemscart, parent, false);
        return new Cart_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Cart_Adapter.MyViewHolder holder, final int position) {

        holder.T_Name.setText(filteredList.get(position).getMealName());

        double value = Double.parseDouble(filteredList.get(position).getMealPrice());
        value =Double.parseDouble(new DecimalFormat("##.####").format(value));
        holder.T_Price.setText(value+" "+con.getResources().getString(R.string.sar));
        holder.counter.setText(filteredList.get(position).getQty());
        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
        holder.T_Name.setTypeface(typeface);
        holder.T_Price.setTypeface(typeface);
        holder.counter.setTypeface(typeface);
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int countity=Integer.parseInt(filteredList.get(position).getQty());
                int count=Integer.parseInt(holder.counter.getText().toString());
                    count++;
                    holder.counter.setText(count + "");
                    count_view.count_plus(String.valueOf(filteredList.get(position).getMenuDetailsId()));
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count=Integer.parseInt(holder.counter.getText().toString());
                if(count>1) {
                    count--;
                    holder.counter.setText(count + "");
                    count_view.count_minus(String.valueOf(filteredList.get(position).getMenuDetailsId()));
                }
            }
        });

        String i = filteredList.get(position).getMealImage();
        Uri u = Uri.parse(i);
        holder.progressBar.setVisibility(View.VISIBLE);

        Glide.with(con)
                .load("http://raaleat.com/"+u)
                .apply(new RequestOptions().override(500,500))

                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }
                })
                .into(holder.Image_product);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filteredList.get(position).getQty()!=null) {
                    count_view.delete(String.valueOf(filteredList.get(position).getMenuDetailsId()),
                            String.valueOf(position));
                    filteredList.remove(position);
                }
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

