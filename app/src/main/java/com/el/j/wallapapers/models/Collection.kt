package com.el.j.wallapapers.models

import com.google.gson.annotations.SerializedName

data class Collection( @SerializedName("id") val id: String,
                       @SerializedName("title") val title: String,
                       @SerializedName("description") val description: String,
                       @SerializedName("published_at") val published_at: String,
                       @SerializedName("updated_at") val updated_at: String,
                       @SerializedName("curated") val curated: Boolean,
                       @SerializedName("total_photos") val total_photos: String,
                       @SerializedName("private") val private: Boolean,
                       @SerializedName("share_key") val share_key: String,
                       @SerializedName("cover_photo") val cover_photo: Cover_Photo,
                       @SerializedName("urls") val  urls: Urls) {
}
