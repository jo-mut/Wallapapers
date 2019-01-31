package com.el.j.wallapapers

import android.arch.lifecycle.LiveData

import android.support.annotation.WorkerThread
import com.el.j.wallapapers.models.Photo

class WallpaperRepository(val wallpapersDao: WallpapersDao) {
    val allWallpapers: LiveData<MutableList<Wallpaper>> = wallpapersDao.getImages()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertWallpaper(photo: Wallpaper){
        wallpapersDao.addWallpaper(photo)
    }
}