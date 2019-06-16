package com.otlb.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.otlb.Adapter.SliderPagerAdapter;
import com.otlb.MainActivity;
import com.raaleat.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Slider extends AppCompatActivity {
    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    SliderPagerAdapter sliderPagerAdapter;
    ArrayList<String> slider_image_list;
    private TextView[] dots;
    int page_position = 0;
    ViewPager viewPager;
    int[]images;
    String[] Names;
    String [] Descriptions;
    TextView skip;
    private static int CURRENT_PAGE=0;
    private static int NUM_PAGES=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slider);
        skip=findViewById(R.id.skipp);
        Names=new String[]
                {
                        getResources().getString(R.string.explore),getResources().getString(R.string.fastdelivery),getResources().getString(R.string.payit)
                };
        Descriptions=new String[]
                {
                        getResources().getString(R.string.findbestrestaurant),
                        getResources().getString(R.string.youwilldelivery),
                        getResources().getString(R.string.payindoor)
                };
        images=new int[]
                {
                        R.drawable.sliderthree,
                        R.drawable.slierone,
                        R.drawable.slidertwo
                };



//// method for adding indicators
//        addBottomDots(0);
//
//        final Handler handler = new Handler();
//
//        final Runnable update = new Runnable() {
//            public void run() {
//                if (page_position == slider_image_list.size()) {
//                    page_position = 0;
//                } else {
//                    page_position = page_position + 1;
//                }
//                vp_slider.setCurrentItem(page_position, true);
//            }
//        };
//
//        new Timer().schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                handler.post(update);
//            }
//        }, 100, 5000);
//
//
//    }
//
//    private void init() {
//
////        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
////        getSupportActionBar().hide();
//        vp_slider = (ViewPager) findViewById(R.id.vp_slider);
//        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);
//
//        slider_image_list = new ArrayList<>();
//
//        slider_image_list.add("");
//        slider_image_list.add("");
//        slider_image_list.add("");
////        slider_image_list.add("http://images.all-free-download.com/images/graphiclarge/bird_mountain_bird_animal_226401.jpg");
//
//
//        sliderPagerAdapter = new SliderPagerAdapter(Slider.this, slider_image_list);
//        vp_slider.setAdapter(sliderPagerAdapter);
//
//        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                addBottomDots(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
//
//    private void addBottomDots(int currentPage) {
//        dots = new TextView[slider_image_list.size()];
//
//        ll_dots.removeAllViews();
//        for (int i = 0; i < dots.length; i++) {
//            dots[i] = new TextView(this);
//            dots[i].setText(Html.fromHtml("&#8226;"));
//            dots[i].setTextSize(35);
//            dots[i].setTextColor(Color.parseColor("#000000"));
//            ll_dots.addView(dots[i]);
//        }
//
//        if (dots.length > 0)
//            dots[currentPage].setTextColor(Color.parseColor("#FFFFFF"));
//    }
//        }
        viewPager=findViewById( R.id.vp_slider );
//        skip=findViewById( R.id.view_pager_text_skip );
        sliderPagerAdapter=new SliderPagerAdapter( Slider.this,images,Names,Descriptions );
        viewPager.setAdapter( sliderPagerAdapter );
        CircleIndicator circleIndicator=findViewById( R.id.view_pager_circle_indicator );
        circleIndicator.setViewPager( viewPager );


        viewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CURRENT_PAGE=position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if(state==ViewPager.SCROLL_STATE_IDLE)
                {
                    int pagesCount=images.length;
                    /*if(CURRENT_PAGE==0)
                    {
                        viewPager.setCurrentItem( pagesCount-1,false );
                    }else if(CURRENT_PAGE==pagesCount-1)
                    {
                        viewPager.setCurrentItem( 0,false );
                    }*/

                }
            }
        } );
      /* final Handler handler=new Handler(  );
        final Runnable runnableUpdate=new Runnable() {
            @Override
            public void run() {
               if(CURRENT_PAGE==NUM_PAGES)
               {
                   CURRENT_PAGE=0;
               }
               viewPager.setCurrentItem( CURRENT_PAGE++,true );
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnableUpdate);
            }
        }, 2000, 2000);*/

        skip.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( Slider.this, MainActivity.class );
                startActivity( intent );
                finish();
            }
        } );

    }
}
