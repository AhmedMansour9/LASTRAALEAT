package com.otlb.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.otlb.R;

import java.util.ArrayList;

public class SliderPagerAdapter extends PagerAdapter {
    Context context;
    int[]images;
    String []Names;
    String []Descriptions;
    LayoutInflater layoutInflater;
    // int position=3;


    public SliderPagerAdapter(Context context, int[] images, String[] names, String[] descriptions) {
        this.context = context;
        this.images = images;
        Names = names;
        Descriptions = descriptions;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==( (RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView;
        TextView textNames;
        TextView textDescriptions;
//        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.item_view_pager,container,false );
        imageView=view.findViewById( R.id.item_view_pager_image );
        textNames=view.findViewById( R.id.item_view_pager_text_name );
        textDescriptions=view.findViewById( R.id.item_view_pager_text_description );

        imageView.setBackgroundResource( images[position] );
        textNames.setText(Names[position]);
        textDescriptions.setText(Descriptions[position]);

        ((ViewPager)container).addView( view );
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ((ViewPager)container).removeView( (RelativeLayout)object );
    }

}