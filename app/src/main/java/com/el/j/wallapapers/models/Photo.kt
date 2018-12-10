package com.el.j.wallapapers.models

import com.google.gson.annotations.SerializedName

data class Photo(@SerializedName("id") val id: String,
                 @SerializedName("title") val title: String,
                 @SerializedName("created_at") val created_at: String,
                 @SerializedName("updatad_at") val updatad_at: String,
                 @SerializedName("width") val width: String,
                 @SerializedName("height") val height: String,
                 @SerializedName("color") val color: String,
                 @SerializedName("likes") val likes: String,
                 @SerializedName("liked_by_user") val liked_by_user: Boolean,
                 @SerializedName("description") val description: String,
                 @SerializedName("urls") val  urls: Urls) {
}
