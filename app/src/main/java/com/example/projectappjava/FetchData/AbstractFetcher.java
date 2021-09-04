package com.example.projectappjava.FetchData;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public abstract class AbstractFetcher {

    protected String connectionAdr;
    protected String requestResult;

    protected void fetch(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner sc = null;
                URL url = null;
                try {
                    url = new URL(connectionAdr);
                    sc = new Scanner(url.openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                StringBuilder sb = new StringBuilder();
                while (true) {
                    assert sc != null;
                    if (!sc.hasNext()) break;
                    sb.append(sc.next() + " ");
                }
                requestResult = sb.toString();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(requestResult);

    }
    public String getRequestResult() {
        return requestResult;
    }


}
