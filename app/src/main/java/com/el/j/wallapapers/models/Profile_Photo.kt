package com.el.j.wallapapers.models

import com.google.gson.annotations.SerializedName

data class Profile_Photo(@SerializedName("email") val email: String,
                         @SerializedName("medium") val medium: String,
                         @SerializedName("large") val large: String) {
}
