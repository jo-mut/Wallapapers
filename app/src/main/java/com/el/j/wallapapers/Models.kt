package com.el.j.wallapapers

import com.google.gson.annotations.SerializedName


data class Results(@SerializedName("id") val id: String,
                   @SerializedName("title") val title: String,
                   @SerializedName("descccription") val description: String,
                   @SerializedName("published_at") val published_at: String,
                   @SerializedName("curated") val curated: String,
                   @SerializedName("featured") val featured: String,
                   @SerializedName("total_photos") val total_photos: String,
                   @SerializedName("private") val private: String,
                   @SerializedName("share_key") val share_key: String,
                   @SerializedName("urls")val url: Urls){
}

data class Urls(@SerializedName("urls") val urls: Map<String, String>,
                @SerializedName("raw") val raw: String,
                @SerializedName("full") val full: String,
                @SerializedName("regular") val regular: String,
                @SerializedName("thumb") val thumb: String){

}