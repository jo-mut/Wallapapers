package com.el.j.wallapapers.fragments


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
import com.el.j.wallapapers.activities.SearchActivity
import com.el.j.wallapapers.adapters.CollectionsRecyclerAdapter
import com.el.j.wallapapers.models.Collection
import com.el.j.wallapapers.models.Photo
import com.el.j.wallapapers.services.WallpaperService
import kotlinx.android.synthetic.main.fragment_search_collections.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchCollectionsFragments : Fragment() {
    private var query: String? = null
    lateinit var searchView: SearchView
    lateinit var collectionsRecyclerAdapter: CollectionsRecyclerAdapter

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
                getCollections(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                getCollections(newText)
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
        var view = inflater.inflate(R.layout.fragment_search_collections, container, false)
        return view;
    }

    fun getCollections(text: String?){
        val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();

        val wallpaperService: WallpaperService = retrofit.create(WallpaperService::class.java)
        val call = wallpaperService.searchCollections(Constants.ACCESS_KEY,10, text)

        call.enqueue(object : Callback<MutableList<Collection>> {
            override fun onFailure(call: Call<MutableList<Collection>>?, t: Throwable?) {
                println("failed" + t.toString())
            }

            override fun onResponse(call: Call<MutableList<Collection>>?, response: Response<MutableList<Collection>>?) {
                if (response != null) {
                    collectionsRecyclerAdapter = CollectionsRecyclerAdapter(context!!, response.body())
                    var layoutManager = LinearLayoutManager(context)
                    searchRecyclerView.adapter = collectionsRecyclerAdapter;
                    searchRecyclerView.layoutManager = layoutManager;
                    searchRecyclerView.setHasFixedSize(false)


                } else {

                }
            }
        })


    }


}
