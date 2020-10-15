package com.hookiz.websocket.server

import com.hookiz.websocket.objects.FeedItem

interface OnMessage {

    fun onMessage(iten : FeedItem)
    fun onError()

}