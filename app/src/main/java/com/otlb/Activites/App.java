package com.otlb.Activites;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.google.firebase.FirebaseApp;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
    }
}
