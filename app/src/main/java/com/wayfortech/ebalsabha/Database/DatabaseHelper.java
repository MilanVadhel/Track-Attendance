package com.wayfortech.ebalsabha.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wayfortech.ebalsabha.Database.TableCreator;

import static com.wayfortech.ebalsabha.Constants.MYTAG;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="ebalsabha.db";
    public static final int DATABASE_VERSION=18;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //create database and tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(MYTAG, "onCreate: Database Created");
        sqLiteDatabase.execSQL(TableCreator.SQL_CREATE_ATTENDANCE_TABLE);
        sqLiteDatabase.execSQL(TableCreator.SQL_CREATE_CHILD_INFO_TABLE);

    }

    //delete database if schema is changed
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(MYTAG, "onUpgrade: Database Upgraded");
        sqLiteDatabase.execSQL(TableCreator.SQL_DELETE_CHILDINFO_TABLE);
        sqLiteDatabase.execSQL(TableCreator.SQL_DELETE_ATTENDANCE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
