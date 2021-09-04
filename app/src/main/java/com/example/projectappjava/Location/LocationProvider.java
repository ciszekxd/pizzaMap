package com.example.projectappjava.Location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public final class LocationProvider {
    static private LocationFinder locationSingleton;




    public static LocationFinder getInstance(Context context, Activity activity, LocationManager systemService) {
        if (locationSingleton == null) {

            int permissionCheck = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
                // ask permissions here using below code
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);
            }

            locationSingleton = new LocationFinder(
                    systemService,
                    context
            );
        }
        return locationSingleton;
    }

}
