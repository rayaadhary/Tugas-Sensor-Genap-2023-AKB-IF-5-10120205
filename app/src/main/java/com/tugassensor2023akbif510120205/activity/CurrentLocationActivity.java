package com.tugassensor2023akbif510120205.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tugassensor2023akbif510120205.R;
import com.tugassensor2023akbif510120205.fragment.CurrentLocationFragment;
import com.tugassensor2023akbif510120205.fragment.InfoFragment;
import com.tugassensor2023akbif510120205.fragment.NearbyPlacesFragment;
import com.tugassensor2023akbif510120205.fragment.ProfileFragment;

public class CurrentLocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient client;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_current_location);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewCurrent);
        bottomNavigationView.setSelectedItemId(R.id.location);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new InfoFragment());
                    return true;
                case R.id.location:
                    replaceFragment(new CurrentLocationFragment());
                    return true;
                case R.id.resto:
                    replaceFragment(new NearbyPlacesFragment());
                    return true;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    return true;
            }
            return false;
        });

        client = LocationServices.getFusedLocationProviderClient(this);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_current, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        getCurrentLocation(googleMap);
    }

    private void getCurrentLocation(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CurrentLocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng lokasi = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions options = new MarkerOptions().position(lokasi).title("CURRENT LOCATION");
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 17));
                    googleMap.addMarker(options);
                }
            }
        });
    }
}
