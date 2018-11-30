package com.el.j.wallapapers

import com.google.gson.annotations.SerializedName

data class Wallpaper(@SerializedName("total") val total: String,
                     @SerializedName("total_pages") val total_pages: String,
                     @SerializedName("results") val results: Results) {
}