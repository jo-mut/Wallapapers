package com.el.j.wallapapers.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.el.j.wallapapers.R

class PhotosViewHolder(view: View): RecyclerView.ViewHolder(view){
    internal var photoImageView: ImageView
    internal var saveImageView: ImageView
    internal var deleteImageView: ImageView
    internal var photoConstraintLayout: ConstraintLayout


    init {
        photoConstraintLayout = view.findViewById(R.id.photoConstraintLayout);
        photoImageView = view.findViewById(R.id.photoImageView)
        saveImageView = view.findViewById(R.id.saveImageView)
        deleteImageView = view.findViewById(R.id.deleteImageView)
    }


}