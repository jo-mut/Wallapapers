package com.el.j.wallapapers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.el.j.wallapapers.models.Wallpaper

class WallpaperAdapter(private val context: Context)
    : RecyclerView.Adapter<PhotosViewHolder>() {

    lateinit var mutableList: MutableList<Wallpaper>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setWallpapers(photos: MutableList<Wallpaper>) {
        this.mutableList = photos
        notifyDataSetChanged()
    }

}