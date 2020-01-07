package com.el.j.wallapapers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseManager {
    // Context Object
    private Context mContext;
    // The name of the database
    public static final String DATABASE_NAME = "wallapapers";
    // The database table name
    public static final String DATABASE_TABLE = "wallapapers_table";
    // The version of the database
    public static final int DATABASE_VERSION = 0;
    // The id of the row
    public static final String ROW_ID = "_id";
    // The name of the row
    public static final String ROW_NAME = "name";
    // The image saved in a row
    public static final String ROW_IMAGE = "image";

    // SQLite Helper object
    private SQLiteHelper mSQLiteHelper;
    // Database Object
    private SQLiteDatabase mSQLiteDatabase;

    public DatabaseManager() {
        this.mSQLiteHelper = new SQLiteHelper(mContext);
        this.mSQLiteDatabase = mSQLiteHelper.getWritableDatabase();

    }


    private ContentValues prepareContentValue(Wallpaper wallpaper) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseManager.ROW_ID, wallpaper.getId());
        cv.put(DatabaseManager.ROW_NAME, wallpaper.getName());
        cv.put(DatabaseManager.ROW_IMAGE, wallpaper.getImage());

        return cv;
    }

    public boolean savePhoto(Wallpaper wallpaper) {
        long result = this.mSQLiteDatabase.insert(DatabaseManager.DATABASE_TABLE,
                null, prepareContentValue(wallpaper));
        return result > 0;
    }

    public boolean deletePhoto(int id) {
        long result = this.mSQLiteDatabase.delete(DatabaseManager.DATABASE_TABLE,
                DatabaseManager.ROW_ID + "=" + id, null);
         return result > 0;
    }


}
