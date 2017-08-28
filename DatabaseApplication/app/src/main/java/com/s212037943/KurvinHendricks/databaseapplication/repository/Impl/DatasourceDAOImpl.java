package com.s212037943.KurvinHendricks.databaseapplication.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.s212037943.KurvinHendricks.databaseapplication.Settings;
import com.s212037943.KurvinHendricks.databaseapplication.User;
import com.s212037943.KurvinHendricks.databaseapplication.repository.DatasourceDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 8/17/2014.
 */
public class DatasourceDAOImpl implements DatasourceDAO {

    private SQLiteDatabase database;
    private DBAdapter dbHelper;

    private int UserSize;

    public DatasourceDAOImpl(Context context){
        dbHelper = new DBAdapter(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    @Override
    public void createSettings(Settings settings){
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_URL, settings.getUrl());
        // Settings URL
        // Inserting Row
        database.insert(DBAdapter.TABLE_SETTINGS, null, values);
        close();
    }

    @Override
    public void updateSettings(Settings settings) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_URL, settings.getUrl());
        // updating row
        database.update(DBAdapter.TABLE_SETTINGS, values, DBAdapter.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(settings.getId())});
        close();
    }

    @Override
    public Settings findSettingsByID(int id) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = database.query(DBAdapter.TABLE_SETTINGS,
                new String[]{DBAdapter.COLUMN_ID, DBAdapter.COLUMN_URL}
        , DBAdapter.COLUMN_ID + " =? ", new String[]{String.valueOf(id)}
            , null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Settings settings = new Settings();
        settings.setId(cursor.getInt(0));
        settings.setUrl(cursor.getString(1));
        close();

        return settings;
    }
    /*\begin {1stlisting}[numbers=left], label=codelist= ContactsContract.Data Source
    ce Helper Class]*/

    @Override
    public void deleteSettings(Settings settings) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.delete(DBAdapter.TABLE_SETTINGS, DBAdapter.COLUMN_ID
        + " =? ", new String[]{String.valueOf(settings.getId())});
        close();
    }

    @Override
    public Settings getSettings() {
        String selectQuery = "SELECT * FROM " + DBAdapter.TABLE_SETTINGS;
        final Settings settings = new Settings();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                settings.setId(cursor.getInt(0));
                settings.setUrl(cursor.getString(1));
            }while(cursor.moveToNext());
        }

        close();
        return settings;
    }

    @Override
    public void createUser(User user) {
        try {
            open();
        } catch (SQLException e) {
            Log.d("Exception on insert", "sucks");
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_EMAIL, user.getEmail());
        values.put(DBAdapter.COLUMN_AUTH, user.getAuth());
        database.insert(DBAdapter.TABLE_USER, null, values);
        Log.i("inserted", "hopefully");
        close();
    }

    @Override
    public void updateUser(User user) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(DBAdapter.COLUMN_EMAIL, user.getEmail());
        values.put(DBAdapter.COLUMN_AUTH, user.getAuth());
        database.update(DBAdapter.TABLE_USER, values,
                DBAdapter.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(user.getId())});

        close();
    }

    @Override
    public User findUserByID(int id) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = database.query(DBAdapter.TABLE_USER,
                new String[]{DBAdapter.COLUMN_ID, DBAdapter.COLUMN_EMAIL,
                DBAdapter.COLUMN_AUTH}, DBAdapter.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        User user = new User();
        user.setId(cursor.getInt(0));
        user.setEmail(cursor.getString(1));
        user.setAuth(cursor.getString(2));
        close();
        return user;
    }

    /*\begin{lstlisting } [ numbers=left , l a b e l=codelist , caption= Data Source ←�
    Helper Class ]*/

    @Override
    public void deleteUser(User user) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        database.delete(DBAdapter.TABLE_USER,
                DBAdapter.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        close();
    }

    @Override
    public User getUser() {
        String selectQuery = "SELECT * FROM " + DBAdapter.TABLE_USER;
        final User user = new User();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Cursor cursor = database.rawQuery(selectQuery, null);

        UserSize = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                user.setId(cursor.getInt(0));
                user.setEmail(cursor.getString(1));
                user.setAuth(cursor.getString(2));
                //Log.i(user.getId()+ " " , "user id");
            }while(cursor.moveToNext());
        }

        close();
        return user;
    }

    @Override
    public List<Settings> getSettingsList() {
        String selectQuery = "SELECT * FROM " + DBAdapter.TABLE_SETTINGS;
        List<Settings> sets = new ArrayList<Settings>();
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                final Settings settings = new Settings();
                settings.setId(cursor.getInt(0));
                settings.setUrl(cursor.getString(1));
                sets.add(settings);
            } while (cursor.moveToNext());
        }

        close();
        return sets;
    }

    public int getUserSize(){
        return UserSize;
    }
}
