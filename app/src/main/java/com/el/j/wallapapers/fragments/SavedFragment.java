package com.el.j.wallapapers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.el.j.wallapapers.R;
import com.el.j.wallapapers.adapters.SavedRecyclerViewAdapter;
import com.el.j.wallapapers.database.DatabaseManager;
import com.el.j.wallapapers.models.Wallpaper;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedFragment extends Fragment {
    private RecyclerView savedPhotosRecyclerView;
    private SavedRecyclerViewAdapter mSavedRecyclerViewAdapter;
    private List<Wallpaper> wallpapers = new ArrayList<>();
    private DatabaseManager mDatabaseManager;

    public SavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        savedPhotosRecyclerView = view.findViewById(R.id.savedPhotosRecyclerView);
        mDatabaseManager = new DatabaseManager(getContext());
        getSavedPhotos();
        setRecyclerView();
        return view;
    }

    private void getSavedPhotos() {
        wallpapers = mDatabaseManager.getSavedPhotos();
    }

    private void setRecyclerView() {
        mSavedRecyclerViewAdapter = new SavedRecyclerViewAdapter(getContext(), wallpapers);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        savedPhotosRecyclerView.setAdapter(mSavedRecyclerViewAdapter);
        savedPhotosRecyclerView.setHasFixedSize(false);
        savedPhotosRecyclerView.setLayoutManager(linearLayout);
    }

}
