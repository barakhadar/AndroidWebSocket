package com.hookiz.websocket.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hookiz.websocket.objects.FeedItem
import com.hookiz.websocket.server.OnMessage
import com.hookiz.websocket.server.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedViewModel ( application : Application) : AndroidViewModel( application ) {

    @Inject
    lateinit var repository : Repository
    val feedItemsList = MutableLiveData<MutableList<FeedItem>>()

    init {
        feedItemsList.value = ArrayList()
    }

    fun initLiveData() {
        startSocket()
    }

    fun startSocket(){
        viewModelScope.launch {
            repository.initWebSocket( object : OnMessage {
                override fun onMessage(iten: FeedItem) {
                    viewModelScope.launch(Dispatchers.Main) {
                        feedItemsList.value?.add(iten)
                        feedItemsList.value = feedItemsList.value
                    }
                }

                override fun onError() {

                }
            } )
        }
    }

    fun killWebSocket(){
        repository.killWebSocket()
    }

}