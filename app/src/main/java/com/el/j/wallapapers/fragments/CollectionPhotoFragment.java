package com.el.j.wallapapers.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.el.j.wallapapers.Constants;
import com.el.j.wallapapers.R;
import com.el.j.wallapapers.adapters.BrowsePagerAdapter;
import com.el.j.wallapapers.models.Photo;
import com.el.j.wallapapers.services.WallpaperService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionPhotoFragment extends Fragment {
    private ViewPager mBrowsePhotosViewPager;
    private List<Photo> mCollectionPhotos = new ArrayList<>();
    private BrowsePagerAdapter mBrowsePhotosAdapter;
    private SearchView mSearchView;

    public CollectionPhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getCollectionPhotos(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_browse_photo, container, false);
        mBrowsePhotosViewPager = view.findViewById(R.id.browsePhotosViewPager);
        mBrowsePhotosAdapter = new BrowsePagerAdapter(getContext());
        getCollectionPhotos("bus");


        return view;
    }

    private void  getCollectionPhotos(String text) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.getBASE_URL())
                .build();

        WallpaperService wallpaperService = retrofit.create(WallpaperService.class);
        wallpaperService.searchPhotos(Constants.getACCESS_KEY(), 10, text)
                .enqueue(new Callback<List<Photo>>() {
                    @Override
                    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                        if (response != null) {
                            mBrowsePhotosAdapter.addPhotos(response.body());
                            mBrowsePhotosAdapter.notifyDataSetChanged();
                            Log.d("browse photos", response.body().toString());

                        }else {
                            Log.d("browse photos", response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Photo>> call, Throwable t) {

                    }
                });
    }

}
