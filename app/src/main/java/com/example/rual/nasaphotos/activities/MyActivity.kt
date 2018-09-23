package com.example.rual.nasaphotos.activities

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.rual.nasaphotos.R
import com.example.rual.nasaphotos.model.Photos
import com.example.rual.nasaphotos.presenters.MyActivityPresenter
import kotlinx.android.synthetic.main.my_activity.*
import retrofit2.Call
import retrofit2.Response


//https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY
class MyActivity : AppCompatActivity(), MyActivityContract.View<MyActivityContract.Presenter>{

    companion object {
        const val BASE_URL="https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"
        val TAG = MyActivity::class.simpleName
    }

    var mPresenter : MyActivityContract.Presenter? = null
    var mPhotos: Photos? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.my_activity)

        setPresenter(MyActivityPresenter(this))

    }

    override fun onResume() {
        super.onResume()
        mPresenter?.getVideoList()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showGetVideoList(response: Response<Photos>?) {
        this.mPhotos = response?.body()

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(mPhotos!!)

        this.mPhotos?.let {
            viewAdapter = MyAdapter(mPhotos!!)
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

    override fun showGetVideoOnFailure(call: Call<Photos>?, t: Throwable?) {
        Log.e(TAG, "Failure:" + t)
        Log.e(TAG, "Failure:" + call?.request()?.url())
    }

    override fun setPresenter(presenter: MyActivityContract.Presenter) {
        mPresenter = presenter
    }

}