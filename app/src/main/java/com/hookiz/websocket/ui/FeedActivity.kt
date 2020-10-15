package com.hookiz.websocket.ui

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hookiz.websocket.R
import com.hookiz.websocket.objects.App
import com.hookiz.websocket.objects.FeedItem
import kotlinx.android.synthetic.main.activity_main.*


class FeedActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "FeedActivity"
    }

    lateinit var viewModel : FeedViewModel

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Incoming Item Feed"
        setContentView(R.layout.activity_main)

        initViewModel()
        initListeners()
    }

    fun initListeners(){
        buttonStart.setOnClickListener {
            viewModel.initLiveData()
        }
        buttonStop.setOnClickListener {
            viewModel.killWebSocket()
        }
    }

    private fun initViewModel() {
        Log.d( TAG, "initViewModel()" )
        viewModel = ViewModelProvider( this ).get( FeedViewModel::class.java )
        ( application as App).component.inject( viewModel )

        val adapter = FeedAdapter()
        feedRecycler.adapter = adapter
        feedRecycler.layoutManager = LinearLayoutManager(this )

        val feedObserver = Observer<List<FeedItem>> { data ->
            Log.d( TAG, "initViewModel --> Data was updated --> ${data.size}" )
            if (data.isNotEmpty()) {
                adapter.setFeedItems(data)
            }
        }
        viewModel.feedItemsList.observe(this, feedObserver )
//        viewModel.initLiveData()
    }
}
