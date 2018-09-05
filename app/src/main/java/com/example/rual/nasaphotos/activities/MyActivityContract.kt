package com.example.rual.nasaphotos.activities

import com.example.rual.nasaphotos.model.Photos
import retrofit2.Call
import retrofit2.Response

class MyActivityContract {

    interface View<in T : Presenter> {

        fun setPresenter(presenter: T)

        fun showGetVideoOnFailure(call: Call<Photos>?, t: Throwable?)

        fun showGetVideoList(response: Response<Photos>?)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter {
        fun subscribe()

        fun unSubscribe()

        fun getVideoList()
    }

}