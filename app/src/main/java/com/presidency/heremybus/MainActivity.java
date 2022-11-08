package com.presidency.heremybus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View busView1;
    private View busView2;
    private View busView3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bus1 Location
        busView1=findViewById(R.id.bus1);
        busView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Bus1UserMap.class);
                startActivity(intent);
            }
        });

//      Bus2 Location
        busView2 = findViewById(R.id.bus2);
        busView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Bus2UserMap.class);
                startActivity(intent);
            }
        });

//        Bus3 Location
        busView3 = findViewById(R.id.bus3);
        busView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Bus3UserMap.class);
                startActivity(intent);
            }
        });
    }


//    Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

        @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        final SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);

//        Driver Information
        if (id == R.id.driver){
            Intent intent = new Intent(MainActivity.this, driver.class);
            startActivity(intent);
            Toast.makeText(this, "Driver", Toast.LENGTH_SHORT).show();
            return true;
        }

//        Feedback Page
        else if (id == R.id.feedback){
            Intent intent = new Intent(MainActivity.this, feedback.class);
            Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show();
            return true;
        }

//        About Us
        else if (id == R.id.aboutus){
            Intent intent = new Intent(MainActivity.this, driver.class);
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
            return true;
        }

//        Logout User
        else if (id == R.id.logout){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            Toast.makeText(this, "Bye!!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}