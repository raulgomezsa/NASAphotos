package com.example.rual.nasaphotos.activities

import android.app.Activity
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.rual.nasaphotos.R
import com.example.rual.nasaphotos.interfaces.NASAPhotosRetrofit
import kotlinx.android.synthetic.main.my_activity.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY
class MyActivity : Activity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.my_activity)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val request = retrofit.create(NASAPhotosRetrofit::class.java)
        val dataset = request.data.execute().body()

        Log.d("KOTLIN_PRUEBA", "Dataset:\n" + dataset.toString())

        if (dataset!=null) {
            viewManager = LinearLayoutManager(this)
            viewAdapter = MyAdapter(dataset)

            recyclerView = my_recycler_view.apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)

                // use a linear layout manager
                layoutManager = viewManager

                // specify an viewAdapter (see also next example)
                adapter = viewAdapter

            }
        }
    }

}