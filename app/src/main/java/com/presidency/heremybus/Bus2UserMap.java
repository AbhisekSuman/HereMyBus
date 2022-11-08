package com.presidency.heremybus;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.presidency.heremybus.databinding.ActivityBus1UserMapBinding;

import java.util.List;

public class Bus2UserMap extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private ActivityBus1UserMapBinding binding;

    private DatabaseReference reference;
    private LocationManager manager;

    Marker myMarker;        //Marker

    LatLng one = new LatLng(19.318045,84.879853);       //Gp Junction
    LatLng two = new LatLng(19.327829,84.800245);       //Lochapada
    LatLng three = new LatLng(19.220364,84.737663);     //Golantara
    LatLng four = new LatLng(19.204705,84.791419);      //opposite

    LatLngBounds.Builder builder = new LatLngBounds.Builder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBus1UserMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manager= (LocationManager) getSystemService(LOCATION_SERVICE);

        reference= FirebaseDatabase.getInstance().getReference().child("Driver-2");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        readChanges();
    }


    private void readChanges() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    try {
                        DriverLocation location = dataSnapshot.getValue(DriverLocation.class);
                        if (location != null) {
                            myMarker.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
                        }
                    }catch (Exception e){
                        Toast.makeText(Bus2UserMap.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Default Marker
        LatLng sydney = new LatLng(-34, 151);

        builder.include(one);
        builder.include(two);
        builder.include(three);
        builder.include(four);

        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        int padding = (int) (width * 0.20);

        mMap.setLatLngBoundsForCameraTarget(bounds);

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));

        //set zoom to level to current so that you won't be able to zoom out viz. move outside bounds
        mMap.setMinZoomPreference(mMap.getCameraPosition().zoom);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").
                icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_baseline_directions_bus_filled_24)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    //For Bus Marker
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawables = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawables.setBounds(0,0, vectorDrawables.getIntrinsicWidth(), vectorDrawables.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawables.getIntrinsicWidth(), vectorDrawables.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawables.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (location!=null){
            saveLocation(location);
        }else{
            Toast.makeText(this, "No location", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveLocation(Location location) {

        reference.setValue(location);
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    //    For menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu_1) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_1, menu_1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.driver){
            Intent intent = new Intent(Bus2UserMap.this, driver.class);
            startActivity(intent);
            Toast.makeText(this, "Driver", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}