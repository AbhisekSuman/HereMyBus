package com.presidency.heremybus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Driver3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver3);

        getSupportActionBar().setTitle("Driver Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}