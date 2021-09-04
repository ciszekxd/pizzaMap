package com.example.projectappjava.FetchData;

public class SQLFetcher extends AbstractFetcher {


    public SQLFetcher(String url) {

        connectionAdr = url;
        fetch();
    }

}

