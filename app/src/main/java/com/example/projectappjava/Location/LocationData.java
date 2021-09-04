package com.example.projectappjava.Location;

public class LocationData {

    private double longitude;
    private double latitude;
    private String name;

    public LocationData(double longitude, double latitude, String name){
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;

    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

}
