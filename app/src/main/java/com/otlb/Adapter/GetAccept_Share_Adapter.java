package com.otlb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.otlb.Model.GetUsersShare;
import com.otlb.Model.MenuDetails;
import com.otlb.View.AnswerShare_View;
import com.otlb.View.RestaurantDetails_View;
import com.raaleat.R;

import java.util.ArrayList;
import java.util.List;

public class GetAccept_Share_Adapter  extends RecyclerView.Adapter<GetAccept_Share_Adapter.MyViewHolder>{

    public static List<GetUsersShare> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    RestaurantDetails_View restaurantDetails_view;
    List<MenuDetails> list=new ArrayList<>();
    AnswerShare_View answerShare_view;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Name,Phone,Mony,Replay;
        Button accept,cancel;
        public MyViewHolder(View view) {
            super(view);
            Name=view.findViewById(R.id.name);
            Phone=view.findViewById(R.id.phone);
            Mony=view.findViewById(R.id.money);
            accept=view.findViewById(R.id.accept);
            cancel=view.findViewById(R.id.cancel);

        }
    }

    public GetAccept_Share_Adapter(List<GetUsersShare> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public void setClickListener(AnswerShare_View answerShare_view) {
        this.answerShare_view=answerShare_view;
    }
    @Override
    public GetAccept_Share_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.getacceptshare, parent, false);
        return new GetAccept_Share_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GetAccept_Share_Adapter.MyViewHolder holder, final int position) {
        holder.Name.setText(con.getResources().getString(R.string.name)+": "+filteredList.get(position).getFirstName());
        holder.Phone.setText(con.getResources().getString(R.string.phone)+": "+filteredList.get(position).getPhone());
        holder.Mony.setText(con.getResources().getString(R.string.money)+": "+filteredList.get(position).getMoney());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             answerShare_view.Answer("1",String.valueOf(filteredList.get(position)));
            }
        });


        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              answerShare_view.Answer("2",String.valueOf(filteredList.get(position)));
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

