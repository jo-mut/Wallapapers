package com.el.j.wallapapers.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import com.el.j.wallapapers.Constants
import com.el.j.wallapapers.R
import com.el.j.wallapapers.Wallpaper
import com.el.j.wallapapers.WallpaperViewModel
import com.el.j.wallapapers.activities.SearchActivity
import com.el.j.wallapapers.adapters.PhotosRecyclerAdapter
import com.el.j.wallapapers.adapters.WallpaperAdapter
import com.el.j.wallapapers.models.Photo
import com.el.j.wallapapers.services.WallpaperService
import kotlinx.android.synthetic.main.fragment_photos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PhotosFragment : Fragment() {

    lateinit var photosRecyclerAdapter: PhotosRecyclerAdapter
    lateinit var wallpaperAdapter: WallpaperAdapter
    lateinit var viewModel: WallpaperViewModel
    lateinit var photos: MutableList<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId;

        if (id == R.id.action_search) {
            var intent = Intent(activity, SearchActivity::class.java);
            startActivity(intent)
        }

        return true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_photos, container, false)

        getSavedPhotos()

        return view;
    }


    fun getSavedPhotos() {
        viewModel = ViewModelProviders.of(this).get(WallpaperViewModel::class.java)
        wallpaperAdapter = WallpaperAdapter(context!!)
        var layoutManager = LinearLayoutManager(context)
        photosRecyclerView.adapter = wallpaperAdapter;
        photosRecyclerView.layoutManager = layoutManager;
        photosRecyclerView.setHasFixedSize(false)
        viewModel.allWallpapers.observe(this, Observer { photos ->
            photos?.let { wallpaperAdapter.setWallpapers(it) } })

        viewModel.allWallpapers.observe(this, object : Observer<MutableList<Wallpaper>> {
            override fun onChanged(list: MutableList<Wallpaper>?) {
                if (list == null) {
                    getPhotos()
                }
            }
        })

    }

    fun getPhotos(){
        val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();

        val wallpaperService: WallpaperService = retrofit.create(WallpaperService::class.java)
        val call = wallpaperService.getPhotos(Constants.ACCESS_KEY,1)

        call.enqueue(object : Callback<MutableList<Photo>> {
            override fun onFailure(call: Call<MutableList<Photo>>?, t: Throwable?) {
                println("failed" + t.toString())
            }

            override fun onResponse(call: Call<MutableList<Photo>>?, response: Response<MutableList<Photo>>?) {
                if (response != null) {
                    photos = response.body()

                    photosRecyclerAdapter = PhotosRecyclerAdapter(context!!, photos)
                    var layoutManager = LinearLayoutManager(context)
                    photosRecyclerView.adapter = photosRecyclerAdapter;
                    photosRecyclerView.layoutManager = layoutManager;
                    photosRecyclerView.setHasFixedSize(false)

                }
            }
        })

    }


}
