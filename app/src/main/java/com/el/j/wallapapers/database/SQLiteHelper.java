package com.el.j.wallapapers.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context) {
        super(context, DatabaseManager.DATABASE_NAME, null, DatabaseManager.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabase = "CREATE TABLE " + DatabaseManager.DATABASE_TABLE +
                "(" + DatabaseManager.ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + DatabaseManager.ROW_NAME + " TEXT,"
                + DatabaseManager.ROW_IMAGE + " BLOB" + ")";

        db.execSQL(createDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + DatabaseManager.DATABASE_TABLE;
        db.execSQL(dropTable);
    }
}
