package com.example.projectappjava;

import android.location.LocationManager;

import com.example.projectappjava.Location.LocationData;
import com.example.projectappjava.Location.LocationFinder;
import com.example.projectappjava.Location.LocationProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapManager {
    private GoogleMap mMap;
    private LocationFinder locationFinder = LocationProvider.getInstance(null,null, null);
    private NumericOperationUtility nou = new NumericOperationUtility();


    MapManager(GoogleMap map){
        mMap = map;
    }

    public void show(LocationData ld, String title){
        mMap.addMarker(
                new MarkerOptions().position(
                        new LatLng(ld.getLatitude(), ld.getLongitude())
                ).title(title)
        );
    }

    public void showList(List<LocationData> list){
        for (LocationData ld:
             list) {
            mMap.addMarker(
                    new MarkerOptions().position(
                            new LatLng(ld.getLatitude(), ld.getLongitude())
                    ).title("from Show")
            );
        }
    }


    public void getDevLocation(){
        LatLng me = new LatLng(locationFinder.getLatitude(), locationFinder.getLongitude());
        mMap.addMarker(new MarkerOptions().position(me).title("Me"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                me,
                (mMap.getMaxZoomLevel() - mMap.getMinZoomLevel()) * 45/100
                )
        );
    }
}
