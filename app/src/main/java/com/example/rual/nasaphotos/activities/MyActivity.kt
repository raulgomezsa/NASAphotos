package com.example.rual.nasaphotos.activities

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.rual.nasaphotos.R
import com.example.rual.nasaphotos.model.Photos
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.net.URL
import kotlinx.android.synthetic.main.my_activity.*

class MyActivity : Activity(){

    class Conexion : AsyncTask<Void, Void, Photos>() {

        override fun doInBackground(vararg p0: Void?): Photos {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()
            val apiResponse = URL("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY").readText()
            val turnsType = object : TypeToken<Photos>() {}.type
            var myDataset = gson.fromJson<Photos>(apiResponse, turnsType)

            return myDataset
        }

        override fun onPostExecute(result: Photos?) {
            super.onPostExecute(result)


        }

    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_activity)

        var dataset = Conexion().execute().get()

        Log.d("KOTLIN_PRUEBA", "Dataset:\n" + dataset.toString())

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