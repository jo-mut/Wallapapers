package com.el.j.wallapapers.adapters

import android.content.Context
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.el.j.wallapapers.R
import com.el.j.wallapapers.models.Collection

class CollectionsRecyclerAdapter(private val context: Context,
                                 private val mutableList: MutableList<Collection>):
        RecyclerView.Adapter<CollectionsViewHolder>() {

    lateinit var constraintSet: ConstraintSet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_collections, parent, false)
        return CollectionsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        var collection: Collection = mutableList[position];
        var urls: String = collection.urls.regular;

        var width: Int = collection.cover_photo.width as Int
        var height: Int = collection.cover_photo.height as Int
        var ratio: Int = height/width


        constraintSet = ConstraintSet()
        constraintSet.clone(holder.photoConstraintLayout)
        constraintSet.setDimensionRatio(holder.photoImageView.id, "H," + ratio)
        holder.photoImageView.setImageResource(R.drawable.photo_placeholder)
        constraintSet.applyTo(holder.photoConstraintLayout)

        Glide.with(context)
                .load(urls)
                .apply(RequestOptions()
                        .placeholder(R.drawable.photo_placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(holder.photoImageView)
    }
}