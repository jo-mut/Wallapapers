package com.el.j.wallapapers

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlin.coroutines.CoroutineContext

class WallpaperViewModel(application: Application): AndroidViewModel(application) {
    private val respository: WallpaperRepository
    val allWallpapers: LiveData<MutableList<Wallpaper>>
    private val coroutineContext: CoroutineContext
    private val scope = coroutineContext

    init {
        val wallpapersDao = WallpaperDatabase.getDatabase(application, scope).wallpapersDao()
        respository = WallpaperRepository(wallpapersDao)
        allWallpapers = respository.allWallpapers
    }

    suspend fun inserNewWallpaper(photo: Wallpaper) {
        respository.insertWallpaper(photo)
    }
}