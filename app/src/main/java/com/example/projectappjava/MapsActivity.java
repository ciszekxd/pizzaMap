package com.example.projectappjava;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectappjava.FetchData.AbstractFetcher;
import com.example.projectappjava.FetchData.SQLFetcher;
import com.example.projectappjava.Location.LocationData;
import com.example.projectappjava.Location.LocationFinder;
import com.example.projectappjava.Location.LocationProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projectappjava.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private MapManager mMap;
    private ActivityMapsBinding binding;
    private JSONArray jsonDB;
    private LocationFinder lf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lf = LocationProvider.getInstance(
                getBaseContext(),
                this,
                (android.location.LocationManager)getSystemService(Context.LOCATION_SERVICE)
        );
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        AbstractFetcher SQLfetcher = new SQLFetcher("http://10.0.2.2:8080/");



        try {
            jsonDB = new JSONArray(SQLfetcher.getRequestResult());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button pizzaButton = findViewById(R.id.pizzaButton);
        pizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPizzeria();
            }
        });
    }

    private void findPizzeria(){
        NumericOperationUtility nou = new NumericOperationUtility();
        List<LocationData> locationList = new ArrayList<>();
        for (int i =0; i< jsonDB.length(); i++){
            LocationData temp = null;
            try {
                temp = new LocationData(
                        jsonDB.getJSONObject(i).getDouble("Longitude"),
                        jsonDB.getJSONObject(i).getDouble("Latitude"),
                        jsonDB.getJSONObject(i).getString("Name")
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
            locationList.add(temp);
        }
        LocationData mainPoint = new LocationData(lf.getLongitude(),lf.getLatitude(), "Me");
        LocationData result = nou.getTheClosest(mainPoint, locationList);
        mMap.show(result, "Pizzeria: " + result.getName());
        //mMap.showList(locationList);

    }

    private void refreshFragment(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    resetFragment();
                    System.out.println("i am alive");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();


    }

    private void  resetFragment(){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Fragment frg = null;
                frg = getSupportFragmentManager().findFragmentByTag("InfoWindowFragment");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.detach(frg);
                ft.attach(frg);
                ft.commit();
                System.out.println("fragment transaction committed");
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = new MapManager(googleMap);
        mMap.getDevLocation();


    }
}