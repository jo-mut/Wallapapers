package com.el.j.wallapapers.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import com.el.j.wallapapers.Constants

import com.el.j.wallapapers.R
import com.el.j.wallapapers.Wallpaper
import com.el.j.wallapapers.WallpaperViewModel
import com.el.j.wallapapers.adapters.PhotosRecyclerAdapter
import com.el.j.wallapapers.models.Photo
import com.el.j.wallapapers.services.WallpaperService
import kotlinx.android.synthetic.main.fragment_search_photos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchPhotosFragment : Fragment() {
    lateinit var searchView: SearchView
    lateinit var photosRecyclerAdapter: PhotosRecyclerAdapter
    lateinit var viewModel: WallpaperViewModel
    lateinit var photos: MutableList<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu);
        searchView  = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getPhotos(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                getPhotos(newText)
                return  false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId;

        return true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_search_photos, container, false)

        return view
    }



    fun getPhotos(text: String?){
        val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();

        val wallpaperService: WallpaperService = retrofit.create(WallpaperService::class.java)
        val call = wallpaperService.searchPhotos(Constants.ACCESS_KEY, 10, text)

        call.enqueue(object : Callback<MutableList<Photo>> {
            override fun onFailure(call: Call<MutableList<Photo>>?, t: Throwable?) {
                println("failed" + t.toString())
            }

            override fun onResponse(call: Call<MutableList<Photo>>?, response: Response<MutableList<Photo>>?) {
                if (response != null) {
                    photos = response.body()
                    photosRecyclerAdapter = PhotosRecyclerAdapter(context!!, photos)
                    var layoutManager = LinearLayoutManager(context)
                    searchRecyclerView.adapter = photosRecyclerAdapter;
                    searchRecyclerView.layoutManager = layoutManager;
                    searchRecyclerView.setHasFixedSize(false)


                }
            }
        })

    }

}
