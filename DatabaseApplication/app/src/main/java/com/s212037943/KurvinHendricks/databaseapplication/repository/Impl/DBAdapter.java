package com.s212037943.KurvinHendricks.databaseapplication.repository.Impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Home on 8/17/2014.
 */
public class DBAdapter extends SQLiteOpenHelper {

    public static final String TABLE_SETTINGS = "settings";
    public static final String TABLE_USER = "user";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_AUTH = "auth";

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String CREATE_SETTINGS_TABLE = "create table " +
            "IF NOT EXISTS "
                + TABLE_SETTINGS + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_URL + " text not null); ";

    private static final String CREATE_USER_TABLE = "create table " +
            "IF NOT EXISTS "
                + TABLE_USER + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_EMAIL + " text not null, "
                + COLUMN_AUTH + " text not null); ";

    public DBAdapter(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(CREATE_SETTINGS_TABLE);
        database.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBAdapter.class.getName(), "Upgrading database from version "
                        + oldVersion + " to " + newVersion + " , which will destroy" +
                        "all old data"
        );

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }
}
