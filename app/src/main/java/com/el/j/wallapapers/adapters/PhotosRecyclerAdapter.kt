package com.el.j.wallapapers.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.el.j.wallapapers.*
import com.el.j.wallapapers.database.DatabaseManager
import com.el.j.wallapapers.models.Wallpaper
import com.el.j.wallapapers.models.Photo
import java.io.ByteArrayOutputStream

class PhotosRecyclerAdapter(private val context: Context,
                            private var mutableList: MutableList<Photo>)
    : RecyclerView.Adapter<PhotosViewHolder>() {

    lateinit var constraintSet: ConstraintSet
    lateinit var id: String
    lateinit var bmpUri: Uri
    lateinit var databaseManager: DatabaseManager

    init {
        databaseManager = DatabaseManager(context)
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

        holder.deleteImageRelativeLayout.visibility = View.VISIBLE
        holder.saveImageRelativeLayout.visibility = View.VISIBLE

        Glide.with(context)
                .load(url)
                .apply(RequestOptions()
                        .placeholder(R.drawable.photo_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(holder.photoImageView)

        holder.saveImageView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                databaseManager = DatabaseManager(context)
                var wallpaper: Wallpaper? = Wallpaper()
                wallpaper!!.name = photo.title
                wallpaper!!.image = getByteArray(holder.photoImageView)
                if (databaseManager.savePhoto(wallpaper)) {
                    Toast.makeText(context, "Successfully to saved photo", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(context, "Failed to save photo", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    fun getByteArray(imageView: ImageView): ByteArray{
        val drawable: Drawable? = imageView.drawable
        var bmp: Bitmap? = null
        if (drawable is BitmapDrawable) {
            bmp = (imageView.drawable as BitmapDrawable).bitmap
        }
        var baos: ByteArrayOutputStream = ByteArrayOutputStream()
        bmp?.compress(Bitmap.CompressFormat.PNG, 100, baos)
        var byteArray: ByteArray? = baos.toByteArray()
        return byteArray!!
    }

}