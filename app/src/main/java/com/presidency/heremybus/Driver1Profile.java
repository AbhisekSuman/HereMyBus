package com.presidency.heremybus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Driver1Profile extends AppCompatActivity {
    Button location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver1_profile);

        location =findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Driver1Profile.this,Bus1DriverMap.class);
                startActivity(intent);
            }
        });

        onBackPressed();

    }
}