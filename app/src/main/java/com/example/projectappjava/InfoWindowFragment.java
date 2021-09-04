package com.example.projectappjava;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectappjava.FetchData.AbstractFetcher;
import com.example.projectappjava.FetchData.OpenWeatherFetcher;
import com.example.projectappjava.Location.LocationFinder;
import com.example.projectappjava.Location.LocationProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class InfoWindowFragment extends Fragment {

    private JSONObject weatherJson;
    private LocationFinder lf;
    private float longitude;
    private float latitude;
    //private Random r = new Random();


    public InfoWindowFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lf = LocationProvider.getInstance(
                getContext(),
                getActivity(),
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE)
        );
        AbstractFetcher weatherFetcher = new OpenWeatherFetcher(
                "https://api.openweathermap.org/data/2.5/weather?q=Poznan&APPID="
                        + getResources().getString(R.string.openWeatherApi)
        );
        try {
            weatherJson = new JSONObject(weatherFetcher.getRequestResult());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_window, container, false);
        //TextView cityView = view.findViewById(R.id.cityNameTextView);
        //cityView.setText("Poznan");
        view = changerTextViews(view);
        return view;
    }



    private View changerTextViews(View inflatedView){
        TextView cityView = inflatedView.findViewById(R.id.cityNameTextView);
        TextView weatherView = inflatedView.findViewById(R.id.weatherForecastView);
        TextView sunriseView = inflatedView.findViewById(R.id.sunriseView);
        TextView sunsetView = inflatedView.findViewById(R.id.sunsetView);

        try {
            cityView.setText("City: " + weatherJson.getString("name"));// + r.nextInt());
            weatherView.setText("Weather forecast: " + weatherJson
                    .getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description"));
            //sunriseView.setText("Longitude: " + String.valueOf(lf.getLongitude()));
            //sunsetView.setText("Latitude: " + String.valueOf(lf.getLatitude()));

            //sunrise
            Date date = new java.util.Date(
                    weatherJson.getJSONObject("sys")
                            .getLong("sunrise")*1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
            String formattedDate = sdf.format(date);
            sunriseView.setText("Sunrise: " +formattedDate);

            //sunset
            date = new java.util.Date(
                    weatherJson.getJSONObject("sys")
                            .getLong("sunset")*1000L);

            formattedDate = sdf.format(date);
            sunsetView.setText("Sunset: " + formattedDate);


        }catch (JSONException e){
           System.out.println(e);
        }
        return inflatedView;
    }

}