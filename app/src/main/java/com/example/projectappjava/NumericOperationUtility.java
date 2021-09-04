package com.example.projectappjava;

import static java.lang.Math.sqrt;

import com.example.projectappjava.Location.LocationData;

import java.util.List;

public class NumericOperationUtility {

    //function returns index of pointsList element, which is the closest to mainPoint
    public LocationData getTheClosest(LocationData mainPoint, List<LocationData> pointsList){

        double difLatitude;
        double difLongitude;
        double distance;
        double sqLat;
        double sqLon;


        double minDistance = 1000000.0F;
        int minIndex = 0;
        for (int i=0; i<pointsList.size(); i++) {
            difLatitude = mainPoint.getLatitude() - pointsList.get(i).getLatitude();
            difLongitude = mainPoint.getLongitude() - pointsList.get(i).getLongitude();
            sqLat = difLatitude * difLatitude;
            sqLon = difLongitude * difLongitude;
            distance = Math.sqrt(sqLon + sqLat);

            if (distance < minDistance){
                minDistance = distance;
                minIndex = i;
            }
        }
        return pointsList.get(minIndex);

    }

}
