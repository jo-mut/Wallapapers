package com.el.j.wallapapers

import android.arch.persistence.room.PrimaryKey

class Papers {
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
