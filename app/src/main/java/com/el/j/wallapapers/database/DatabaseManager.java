package com.el.j.wallapapers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.el.j.wallapapers.models.Wallpaper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {
    // Context Object
    private Context mContext;
    // The name of the database
    public static final String DATABASE_NAME = "wallapapers";
    // The database table name
    public static final String DATABASE_TABLE = "wallapapers_table";
    // The version of the database
    public static final int DATABASE_VERSION = 1;
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

    public DatabaseManager(Context context) {
        this.mContext = context;
        this.mSQLiteHelper = new SQLiteHelper(mContext);
        this.mSQLiteDatabase = mSQLiteHelper.getWritableDatabase();

    }


    private ContentValues prepareContentValue(Wallpaper wallpaper) {
        ContentValues cv = new ContentValues();
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

    public ArrayList<Wallpaper> getSavedPhotos() {
        ArrayList<Wallpaper> wallpapers = new ArrayList<>();
        this.mSQLiteDatabase = mSQLiteHelper.getReadableDatabase();
        Cursor cursor = mSQLiteDatabase.query(DatabaseManager.DATABASE_TABLE, null,
                null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Wallpaper wallpaper = new Wallpaper();
                wallpaper.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseManager.ROW_NAME)));
                wallpaper.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(DatabaseManager.ROW_IMAGE)));
                wallpapers.add(wallpaper);
            }
        }

        return wallpapers;
    }


}
