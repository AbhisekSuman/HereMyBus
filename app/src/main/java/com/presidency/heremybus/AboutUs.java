package com.presidency.heremybus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.github.barteksc.pdfviewer.PDFView;

public class AboutUs extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        getActionBar().setTitle("About Us");
        getActionBar().setDisplayHomeAsUpEnabled(true);

        PDFView pdfView = findViewById(R.id.about);

        pdfView.fromAsset("aboutus.pdf").load();
    }

}