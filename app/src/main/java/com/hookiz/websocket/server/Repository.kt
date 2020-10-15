package com.hookiz.websocket.server

import android.app.Application
import android.util.Log

class Repository (application : Application) {

    companion object {
        private const val TAG = "Repository"
    }

    fun initWebSocket(listener : OnMessage) {
        Log.d( TAG, "initWebSocket()" )
        ApiClient.initWebSocket( listener )
    }

    fun killWebSocket() {
        Log.d( TAG, "killWebSocket()" )
        ApiClient.killWebSocket()
    }

}