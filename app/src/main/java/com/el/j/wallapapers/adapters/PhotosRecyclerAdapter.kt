package com.el.j.wallapapers.adapters

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.support.constraint.ConstraintSet
import android.support.v4.content.FileProvider
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.el.j.wallapapers.*
import com.el.j.wallapapers.models.Photo
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PhotosRecyclerAdapter(private val context: Context,
                            private var mutableList: MutableList<Photo>)
    : RecyclerView.Adapter<PhotosViewHolder>() {

    lateinit var constraintSet: ConstraintSet
    lateinit var id: String
    lateinit var bmpUri: Uri
    lateinit var wallpaperRepository: WallpaperRepository
    lateinit var wallpapersDao: WallpapersDao


    init {
        val wallpapersDao = WallpaperDatabase.getDatabase(context).wallpapersDao()
        wallpaperRepository = WallpaperRepository(wallpapersDao)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_photos, parent, false)
        return PhotosViewHolder(view)

    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        var photo: Photo = mutableList[position]
        var url: String = photo.urls.regular;

        var width = photo.width.toInt()
        var height = photo.height.toInt()
        var ratio: Int = height/width


        constraintSet = ConstraintSet()
        constraintSet.clone(holder.photoConstraintLayout)
        constraintSet.setDimensionRatio(holder.photoImageView.id, "H," + ratio)
        holder.photoImageView.setImageResource(R.drawable.photo_placeholder)
        constraintSet.applyTo(holder.photoConstraintLayout)

        Glide.with(context)
                .load(url)
                .apply(RequestOptions()
                        .placeholder(R.drawable.photo_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(holder.photoImageView)

        holder.deleteImageView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {

            }
        })

        holder.saveImageView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                id = photo.id;
                getLocalBitmapUri(holder.photoImageView)
                var wallpaper = Wallpaper()
                wallpaper.id = photo.id;
                wallpaper.image = returnImageUri().toString()
                suspend { wallpaperRepository.insertWallpaper(wallpaper) }
            }
        })
    }

    fun returnImageUri(): Uri {
        return bmpUri
    }

    fun getLocalBitmapUri(imageView: ImageView): Uri? {
        // Extract Bitmap from ImageView drawable
        val drawable = imageView.drawable
        var bmp: Bitmap? = null
        if (drawable is BitmapDrawable) {
            bmp = (imageView.drawable as BitmapDrawable).bitmap
        } else {
            return null
        }
        // Store image to default external storage directory
        try {

            val file = File((Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png")
            val out = FileOutputStream(file)
            bmp!!.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.close()
            // wrap File object into a content provider. NOTE: authority here should match authority in manifest declaration
            bmpUri = FileProvider.getUriForFile(context, "com.el.j.wallapapers", file)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bmpUri
    }

}