package com.presidency.heremybus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    private View wdrive;
    private View wuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        wdrive = findViewById(R.id.wdriver);
        wdrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,DriverLogin.class);
                startActivity(intent);
                finish();
            }
        });

        wuser = findViewById(R.id.wuser);
        wuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,login.class);
                startActivity(intent);
                finish();
            }
        });


    }

}