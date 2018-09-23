package com.example.rual.nasaphotos.presenters

import com.example.rual.nasaphotos.activities.MyActivityContract
import com.example.rual.nasaphotos.api.NASAPhotosRetriever
import com.example.rual.nasaphotos.model.Photos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyActivityPresenter (var myActivityView: MyActivityContract.View<MyActivityContract.Presenter>?) :MyActivityContract.Presenter {

    override fun getVideoList() {
        var retriever = NASAPhotosRetriever()

        val callBack = object : Callback<Photos> {
            override fun onFailure(call: Call<Photos>?, t: Throwable?) {
                myActivityView!!.showGetVideoOnFailure(call, t)
            }

            override fun onResponse(call: Call<Photos>?, response: Response<Photos>?) {
                response?.isSuccessful.let {
                    myActivityView?.showGetVideoList(response)
                    myActivityView!!.hideProgress()
                }
            }
        }

        myActivityView!!.showProgress()
        retriever.getNASAPhotos(callBack)

    }

    override fun onResume() {
        myActivityView?.showProgress()

    }

    override fun onDestroy() {
        myActivityView = null
    }


}