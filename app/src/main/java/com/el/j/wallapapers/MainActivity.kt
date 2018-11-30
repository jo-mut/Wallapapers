package com.el.j.wallapapers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var BASE_URL = "https://api.unsplash.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWallpapers()
    }

    fun getWallpapers(){
        var retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        var wallpaperService: WallpaperService = retrofit.create(WallpaperService::class.java)
        var call = wallpaperService.searchPhotos("1", "office")


    }
}
