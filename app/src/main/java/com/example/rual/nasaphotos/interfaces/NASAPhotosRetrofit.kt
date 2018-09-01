package com.example.rual.nasaphotos.interfaces

import com.example.rual.nasaphotos.model.Photos
import retrofit2.Call
import retrofit2.http.GET


interface NASAPhotosRetrofit {
    @get:GET("photos?sol=1000&api_key=DEMO_KEY")
    val data: Call<Photos>
}