package com.example.projectappjava;

import android.content.Context;

public final class DBSingleton {

    private static InnerDBHelper db;

    public static void makeInstance(Context context){
        db = new InnerDBHelper(context);
    }

    public static InnerDBHelper getInstance(){
        return db;
    }
}
