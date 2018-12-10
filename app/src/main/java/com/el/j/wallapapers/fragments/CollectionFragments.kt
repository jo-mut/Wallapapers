package com.el.j.wallapapers.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import com.el.j.wallapapers.Constants

import com.el.j.wallapapers.R
import com.el.j.wallapapers.activities.SearchActivity
import com.el.j.wallapapers.adapters.CollectionsRecyclerAdapter
import com.el.j.wallapapers.models.Collection
import com.el.j.wallapapers.services.WallpaperService
import kotlinx.android.synthetic.main.fragment_photos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CollectionFragments : Fragment() {

    lateinit var collectionsRecyclerAdapter: CollectionsRecyclerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu);
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
        var view = inflater.inflate(R.layout.fragment_collection_fragments, container, false)

        getCollections()

        return view;
    }



    fun getCollections(){
        val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();

        val wallpaperService: WallpaperService = retrofit.create(WallpaperService::class.java)
        val call = wallpaperService.getCollections(Constants.ACCESS_KEY,1)

        call.enqueue(object : Callback<MutableList<Collection>> {
            override fun onFailure(call: Call<MutableList<Collection>>?, t: Throwable?) {
                println("failed" + t.toString())
            }

            override fun onResponse(call: Call<MutableList<Collection>>?, response: Response<MutableList<Collection>>?) {
                if (response != null) {
                    collectionsRecyclerAdapter = CollectionsRecyclerAdapter(context!!, response.body())
                    var layoutManager = LinearLayoutManager(context)
                    photosRecyclerView.adapter = collectionsRecyclerAdapter;
                    photosRecyclerView.layoutManager = layoutManager;
                    photosRecyclerView.setHasFixedSize(false)
                } else {

                }
            }
        })

    }

}
