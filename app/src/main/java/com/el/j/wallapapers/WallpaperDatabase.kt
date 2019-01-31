package com.el.j.wallapapers

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.el.j.wallapapers.models.Photo
import okhttp3.internal.Internal.instance
import kotlin.coroutines.CoroutineContext

@Database(entities = [Wallpaper::class], version = 1)
abstract class WallpaperDatabase: RoomDatabase() {
    abstract fun wallpapersDao(): WallpapersDao

    companion object {

        @Volatile
        private var INSTANCE : WallpaperDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineContext): WallpaperDatabase {
            val temInstance = INSTANCE
            if (instance != null) {
                return temInstance!!
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, WallpaperDatabase::class.java, "wallpaper_database")
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                return instance
            }

        }

//        private class WallpaperDatabaseCallback(val scope: CoroutineContext): RoomDatabase.Callback() {
//            /**
//             * Override the onOpen method to populate the database.
//             */
//            override fun onOpen(db: SupportSQLiteDatabase) {
//                super.onOpen(db)
//            }
//
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//            }
//        }
    }
}