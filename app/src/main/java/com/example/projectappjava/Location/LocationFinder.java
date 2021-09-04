package com.example.projectappjava.Location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;

public class LocationFinder implements LocationListener {



    protected float latitude;
    protected float longitude;
    protected LocationManager locationManager;
    protected LocationListener locationListener;



    @SuppressLint("MissingPermission")
    LocationFinder(LocationManager manager, Context context){

        locationManager = manager;
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, this);
        Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        onLocationChanged(l);

    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

        latitude = (float) location.getLatitude();
        longitude = (float) location.getLongitude();
        System.out.println(latitude + " " + longitude);

    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

}
