package com.hookiz.websocket.objects

import androidx.room.Entity
import com.squareup.moshi.JsonClass

// {"name": "Peas", "weight": "0.8kg", "bagColor": "#00904B"}

@Entity( tableName = "feedItem_table" )
@JsonClass(generateAdapter = true)
data class FeedItem( var name : String, var weight : String, var bagColor : String )