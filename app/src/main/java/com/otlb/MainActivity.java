package com.otlb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.otlb.Activites.Splash;
import com.otlb.Fragments.Login;
import com.raaleat.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new Login()).commit();



    }
}
