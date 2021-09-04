package com.example.projectappjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InnerDBHelper extends SQLiteOpenHelper {


    // Database Info
    private static final String DATABASE_NAME = "CredentialsDb";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Credentials";

    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";



    public InnerDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" +
                ID + " INTEGER PRIMARY KEY," +
                LOGIN + " TEXT," +
                PASSWORD + " TEXT" +
                ")";
        db.execSQL(CREATE_TABLE);

        db.beginTransaction();
        try {
            // The user might already exist in the database (i.e. the same user created multiple posts).

            ContentValues values = new ContentValues();
            values.put(LOGIN, "Admin");
            values.put(PASSWORD, "Admin123");

            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {
             System.out.println("Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isInDb(String login, String password){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<CredentialsData> cdl = new ArrayList<CredentialsData>();
        try {
            if (cursor.moveToFirst()) {
                do {
                    CredentialsData data = new CredentialsData();
                    data.login = cursor.getString(cursor.getColumnIndex(LOGIN));
                    data.password = cursor.getString(cursor.getColumnIndex(PASSWORD));
                    cdl.add(data);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            System.out.println("Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        for (CredentialsData cd: cdl) {
            if (cd.login.equals(login) && cd.password.equals(password)) return true;
        }
        return false;
    }
}
