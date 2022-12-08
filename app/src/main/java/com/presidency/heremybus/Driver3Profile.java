package com.presidency.heremybus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.BackoffPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class Driver3Profile extends AppCompatActivity {

    public Activity activity;
    Button permission, Location;
    private final String[] foreground_permission =
            {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };

    private final String[] background_location = {
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
    };

    private PermissionManager permissionManager;
    private LocationManager locationManager;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver3_profile);

        activity = Driver3Profile.this;
        permission = findViewById(R.id.permission3);
        Location = findViewById(R.id.startservbtn3);

        permissionManager = PermissionManager.getInstance(this);
        locationManager = LocationManager.getInstance(this);

        permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!permissionManager.checkPermissions(foreground_permission)) {
                    permissionManager.askPermissions(Driver3Profile.this, foreground_permission, 100);
                }
            }
        });

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!permissionManager.checkPermissions(background_location)) {
                    permissionManager.askPermissions(Driver3Profile.this, background_location, 200);
                } else {
                    if (locationManager.isLocationEnabled()) {
                        startLocationWork();
                        Intent intent1 = new Intent(Driver3Profile.this, Bus3DriverMap.class);
                        startActivity(intent1);
                    } else {
                        locationManager.createLocationRequest();
                        Toast.makeText(Driver3Profile.this, "Location service is enabled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionManager.handlePermissionResult(Driver3Profile.this, 100, permissions,
                grantResults)) {
            Log.d("TAG", "1");
            if (locationManager.isLocationEnabled()) {
                startLocationWork();
            } else {
                locationManager.createLocationRequest();
                Toast.makeText(Driver3Profile.this, "Location service is enabled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startLocationWork() {
        OneTimeWorkRequest foregroundWorkRequest = new OneTimeWorkRequest.Builder(LocationWork.class)
                .addTag("LocationWork")
                .setBackoffCriteria(BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MAX_BACKOFF_MILLIS, TimeUnit.HOURS)
                .build();

        WorkManager.getInstance(Driver3Profile.this).enqueue(foregroundWorkRequest);
    }

    @Override
    public void onBackPressed() {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_driver, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        final SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);

        if (id == R.id.logout){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(Driver3Profile.this, Welcome.class);
            startActivity(intent);
            Toast.makeText(this, "Bye!!", Toast.LENGTH_SHORT).show();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}