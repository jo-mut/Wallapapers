package com.el.j.wallapapers.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;

import com.el.j.wallapapers.Constants;
import com.el.j.wallapapers.R;
import com.el.j.wallapapers.adapters.BrowsePagerAdapter;
import com.el.j.wallapapers.fragments.CollectionPhotoFragment;
import com.el.j.wallapapers.models.Photo;
import com.el.j.wallapapers.services.WallpaperService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrowsePhotosActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private FrameLayout mBrowsePhotosContainer;
    private ViewPager mBrowsePhotosViewPager;
    private List<Photo> mCollectionPhotos = new ArrayList<>();
    private BrowsePagerAdapter mBrowsePhotosAdapter;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_photos);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setTitle("Browse Collection");

        mBrowsePhotosViewPager = findViewById(R.id.browsePhotosViewPager);
        mBrowsePhotosAdapter = new BrowsePagerAdapter(this);
        mBrowsePhotosViewPager.setAdapter(mBrowsePhotosAdapter);
        getCollectionPhotos("bus");


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
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

        return true;
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
