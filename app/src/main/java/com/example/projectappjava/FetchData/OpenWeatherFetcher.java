package com.example.projectappjava.FetchData;

public class OpenWeatherFetcher extends AbstractFetcher {

    public OpenWeatherFetcher(String url){
        connectionAdr = url;
        fetch();
    }

}
