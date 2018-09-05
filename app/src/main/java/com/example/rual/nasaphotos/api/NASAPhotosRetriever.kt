package com.example.rual.nasaphotos.api

import com.example.rual.nasaphotos.activities.MyActivity
import com.example.rual.nasaphotos.model.Photos
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NASAPhotosRetriever {
    private val photosAPI: NASAPhotosRetrofit

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(MyActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        photosAPI = retrofit.create(NASAPhotosRetrofit::class.java)


    }

    fun getNASAPhotos(callBack: Callback<Photos>) {
        val dataset = photosAPI.data
        dataset.enqueue(callBack)
    }

}