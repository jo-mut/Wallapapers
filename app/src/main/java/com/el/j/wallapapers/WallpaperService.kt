package com.el.j.wallapapers

import retrofit2.http.GET
import retrofit2.http.Query

interface WallpaperService {
    @GET("search/collections{page}{searchTerm}")
    fun searchPhotos(@Query("page") page: String,
                     @Query("searchTerm") searchTerm: String)

}