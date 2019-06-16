package com.otlb.Activites;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.otlb.Fragments.Home;
import com.otlb.Fragments.MyOrders;
import com.otlb.Fragments.NearestBranches;
import com.otlb.Fragments.Notifications;
import com.otlb.Fragments.Offers;
import com.otlb.Fragments.Setting;
import com.otlb.Fragments.TabsLayout;
import com.otlb.Fragments.Wallet;
import com.raaleat.R;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences.Editor Sha;
    public static Boolean Visablty;
    public static ActionBarDrawerToggle toggle;
    public static DrawerLayout drawer;
    public static Toolbar toolbar;
    FragmentManager fragmentManager;
    Fragment fr;
    private int mCurrentSelectedPosition = 0;
    SharedPreferences shared;
    NavigationView navigationView;
    SharedPreferences.Editor share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Cairo-Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_navigation);
        Sha=getSharedPreferences("login",MODE_PRIVATE).edit();
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        toggle.setDrawerIndicatorEnabled(false);

//        toolbar.setNavigationIcon(R.drawable.navigation);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Visablty) {

                if (fr instanceof Offers) {
                    BackToHome();

                } else if (fr instanceof Notifications) {
                    BackToHome();

                }
                else if (fr instanceof NearestBranches) {
                    BackToHome();

                }
                else if (fr instanceof Wallet) {
                    BackToHome();

                } else if (fr instanceof Setting) {
                    BackToHome();

                } else {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        }

        }


        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case R.id.home:
                    mCurrentSelectedPosition = 0;
                    fr = new TabsLayout();
                    break;
                case R.id.offers:
                    mCurrentSelectedPosition = 1;
                    fr = new Offers();
                    break;
                case R.id.nearestrestaurants:
                    mCurrentSelectedPosition = 2;

                    fr = new NearestBranches();
                    break;
//
//                case R.id.myorders:
//                    mCurrentSelectedPosition = 3;
//                    fr = new MyOrders();
//                    break;
//                case R.id.wallet:
//                    mCurrentSelectedPosition = 4;
//
//                    fr = new Wallet();
//                    break;
                case R.id.setting:
                    mCurrentSelectedPosition = 3;

                    fr = new Setting();
                    break;
//        case R.id.signout:
//        mCurrentSelectedPosition = 7;
//        Sha.putString("logggin",null);
//        Sha.apply();
//        startActivity(new Intent(this, MainActivity.class));
//        finish();

                default:
                    mCurrentSelectedPosition = 0;

            }
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            item.setChecked(true);


            fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.flContentss, fr);
            transaction.commit();
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

    private void BackToHome()
    {

        fr = new Home();
        if(fr !=null)
        {
            navigationView.getMenu().getItem(0).setChecked(true);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.flContentss,fr).commit();
        }

    }
}
