package com.el.j.wallapapers

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.el.j.wallapapers.Wallpaper
import com.el.j.wallapapers.models.Photo

@Dao
interface WallpapersDao {
    @Query("SELECT * FROM wallpapers")
    fun getImages(): LiveData<MutableList<Wallpaper>>

    @Query("delete from wallpapers")
    fun deleteAll()

    @Insert
    fun addWallpaper(photo: Wallpaper)
}