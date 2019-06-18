package com.otlb.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.otlb.Model.GetUsersShare;
import com.otlb.Model.MenuDetails;
import com.otlb.Model.MyOrderss;
import com.otlb.View.RestaurantDetails_View;
import com.raaleat.R;

import java.util.ArrayList;
import java.util.List;

public class GetUsers_Share_Adapter  extends RecyclerView.Adapter<GetUsers_Share_Adapter.MyViewHolder>{

    public static List<GetUsersShare> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    RestaurantDetails_View restaurantDetails_view;
    List<MenuDetails> list=new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Name,Phone,Mony,Replay;

        public MyViewHolder(View view) {
            super(view);
            Name=view.findViewById(R.id.name);
            Phone=view.findViewById(R.id.phone);
            Mony=view.findViewById(R.id.money);
            Replay=view.findViewById(R.id.statues);
        }
    }

    public GetUsers_Share_Adapter(List<GetUsersShare> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(RestaurantDetails_View restaurantDetails_view) {
        this.restaurantDetails_view = restaurantDetails_view;

    }
    @Override
    public GetUsers_Share_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.getusersshare, parent, false);
        return new GetUsers_Share_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GetUsers_Share_Adapter.MyViewHolder holder, final int position) {
        holder.Name.setText(con.getResources().getString(R.string.name)+": "+filteredList.get(position).getFirstName());
        holder.Phone.setText(con.getResources().getString(R.string.phone)+": "+filteredList.get(position).getPhone());
        holder.Mony.setText(con.getResources().getString(R.string.money)+": "+filteredList.get(position).getMoney());
        if(filteredList.get(position).getAccept().equals("0")) {
            holder.Replay.setText(con.getResources().getString(R.string.status) + ": " + con.getResources().getString(R.string.noreply));

        }  else if(filteredList.get(position).getAccept().equals("1")) {
            holder.Replay.setText(con.getResources().getString(R.string.status) + ": " + con.getResources().getString(R.string.accept));

        }
        else  if(filteredList.get(position).getAccept().equals("2")) {
            holder.Replay.setText(con.getResources().getString(R.string.status) + ": " + con.getResources().getString(R.string.cancel));

        }
        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
        holder.Name.setTypeface(typeface);
        holder.Mony.setTypeface(typeface);
        holder.Phone.setTypeface(typeface);
        holder.Replay.setTypeface(typeface);

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

