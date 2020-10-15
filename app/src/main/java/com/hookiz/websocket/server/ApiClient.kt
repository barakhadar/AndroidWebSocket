package com.hookiz.websocket.server

import android.util.Log
import com.hookiz.websocket.objects.FeedItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI
import javax.net.ssl.SSLSocketFactory

class ApiClient {

    companion object {

        private const val TAG = "ApiClient"
        private lateinit var webSocketClient: WebSocketClient
        val baseUri: URI? = URI("wss://superdo-groceries.herokuapp.com/receive")

        fun initWebSocket( listener : OnMessage ) {
            Log.d(TAG, "initWebSocket")
            createWebSocketClient(listener, baseUri)
        }

        private fun createWebSocketClient(listener : OnMessage, baseUri: URI?) {
            Log.d(TAG, "createWebSocketClient")

            val socketFactory: SSLSocketFactory = SSLSocketFactory.getDefault() as SSLSocketFactory

            webSocketClient = object : WebSocketClient(baseUri) {

                override fun onOpen(handshakedata: ServerHandshake?) {
                    Log.d(TAG, "onOpen")
                    subscribe()
                }

                override fun onMessage(message: String?) {
                    Log.d(TAG, "onMessage: $message")
                    val moshi = Moshi.Builder().build()
                    val adapter: JsonAdapter<FeedItem> = moshi.adapter(FeedItem::class.java)
                    adapter.fromJson(message)?.let { listener.onMessage(it) }
                }

                override fun onError(ex: Exception?) {
                    Log.d(TAG, "onError: $ex")
                    listener.onError()
                }

                override fun onClose(code: Int, reason: String?, remote: Boolean) {
                    Log.d(TAG, "onClose: $reason")
                }
            }
            webSocketClient.setSocketFactory(socketFactory)
            webSocketClient.connect()
        }

        private fun subscribe() {
            webSocketClient.send("")
        }

        fun killWebSocket() {
            webSocketClient.close()
        }

    }

}