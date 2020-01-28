package com.el.j.wallapapers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.el.j.wallapapers.R;
import com.el.j.wallapapers.models.Photo;

import java.util.ArrayList;
import java.util.List;

public class BrowsePagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<Photo> mPhotos = new ArrayList<>();


    public BrowsePagerAdapter(Context context) {
        this.mContext = context;
    }

    public void addPhotos( List<Photo> photos){
        this.mPhotos = photos;
        Log.d("photo small url", mPhotos.get(0).getUrls().getSmall() + "");
        Log.d("photos added", mPhotos.size() + "");

    }

    @Override
    public int getCount() {
        return mPhotos.size()-1;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater
                .inflate(R.layout.layout_collection_photo, container, false);
        ImageView photoImageView = (ImageView) layout.findViewById(R.id.photoImageView);
        Photo photo = mPhotos.get(position);
        Log.d("photo", photo.toString() + "");
        Log.d("photo small url", photo.getUrls().getSmall() + "");
        Glide.with(mContext)
                .asBitmap()
                .load(photo.getUrls().getRegular())
                .into(photoImageView);
        container.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
