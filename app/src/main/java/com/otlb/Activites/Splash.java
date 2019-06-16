package com.otlb.Activites;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.otlb.Activites.Navigation;
import com.otlb.Fragments.Login;
import com.otlb.MainActivity;
import com.raaleat.R;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Splash extends AppCompatActivity {


    public Splash() {
        // Required empty public constructor
    }

    View view;
    SharedPreferences sha;
    SharedPreferences shared;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash);
        Check_Language();

        final ImageView logo =findViewById(R.id.logo);
//        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.animationsplash);
        Animation animationSlideInLeft = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        animationSlideInLeft.setDuration(3000);

        logo.startAnimation(animationSlideInLeft);
        animationSlideInLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                String login=Check_Login();

                if(login!=null){
                    startActivity(new Intent(Splash.this, Navigation.class));
                    finish();


                }else {
                    startActivity(new Intent(Splash.this, Slider.class));
                    finish();

                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });



    }

    public void Check_Language(){
        shared=getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
        if(Lan!=null) {
            Locale locale = new Locale(Lan);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
    }

    public String Check_Login(){

        sha=getSharedPreferences("login",MODE_PRIVATE);
        String logi=sha.getString("logggin",null);

        return logi;
    }

}

