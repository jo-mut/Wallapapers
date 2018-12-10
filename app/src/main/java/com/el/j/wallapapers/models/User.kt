package com.el.j.wallapapers.models

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id") val id: String,
                @SerializedName("username") val username: String,
                @SerializedName("name") val name: String,
                @SerializedName("portfolio_url") val portfolio_url: String,
                @SerializedName("location") val location: String,
                @SerializedName("total_likes") val total_likes: String,
                @SerializedName("total_photos") val total_photos: String,
                @SerializedName("total_collections") val total_collections: String,
                @SerializedName("profile_image") val profile_image: Profile_Photo) {
}
