package com.el.j.wallapapers.services

import com.el.j.wallapapers.models.Collection
import com.el.j.wallapapers.models.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpaperService {
    @GET("collections")
    fun getCollections(@Query("client_id") client_id: String,
                       @Query("page") page: Int): Call<MutableList<Collection>>

    @GET("search/collections")
    fun searchCollections(@Query("client_id") client_id: String,
                          @Query("per_page") per_page: Int,
                          @Query("query") query: String): Call<MutableList<Collection>>

    @GET("photos")
    fun getPhotos(@Query("client_id") client_id: String,
                  @Query("page") page: Int): Call<MutableList<Photo>>

    @GET("photos")
    fun searchPhotos(@Query("client_id") client_id: String,
                     @Query("per_page") per_page: Int,
                     @Query("query") query: String): Call<MutableList<Photo>>

}