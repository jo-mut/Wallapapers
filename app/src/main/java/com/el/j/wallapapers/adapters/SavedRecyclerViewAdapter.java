package com.el.j.wallapapers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.el.j.wallapapers.R;
import com.el.j.wallapapers.models.Wallpaper;

import java.util.ArrayList;
import java.util.List;

public class SavedRecyclerViewAdapter extends RecyclerView.Adapter<PhotosViewHolder> {
    private Context mContext;
    private List<Wallpaper> wallpapers = new ArrayList<>();

    public SavedRecyclerViewAdapter(Context mContext, List<Wallpaper> wallpapers) {
        this.mContext = mContext;
        this.wallpapers = wallpapers;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photos, parent, false);

        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        Wallpaper wallpaper = wallpapers.get(position);
    }

    @Override
    public int getItemCount() {
        return wallpapers.size() - 1;
    }
}
