package com.el.j.wallapapers

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.el.j.wallapapers.models.Photo

@Entity(tableName = "wallpapers")
class Wallpaper {

    @PrimaryKey(autoGenerate = true)
    lateinit var image: String
    lateinit var id: String

    constructor()

    fun setImage() : String {
        return image
    }

    fun setId(): String {
        return id
    }


}