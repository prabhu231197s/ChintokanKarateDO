package com.example.prabhusivanandam.chintokankaratedo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Prabhu Sivanandam on 10-May-17.
 */

public class Aboutus extends AppCompatActivity {

    //Nothing in functionality just display information about dojo refer aboutus.xml
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        toolbar=(Toolbar)findViewById(R.id.t);
        setSupportActionBar(toolbar);
    }
}
