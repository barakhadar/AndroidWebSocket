package com.hookiz.websocket.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hookiz.websocket.R
import com.hookiz.websocket.objects.FeedItem
import kotlinx.android.synthetic.main.feed_item.view.*

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedItemViewHolder>() {
    private val TAG = "FeedAdapter"
    private var feedItems : MutableList<FeedItem> = mutableListOf()

    fun setFeedItems( items: List<FeedItem> ) {
        val index = if (items.size > 1) items.size-1 else 0
        Log.d(TAG, "setFeedItems() items.size --> ${items.size}" )
        this.feedItems.add(0,items[index])
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ) : FeedItemViewHolder {
        return FeedItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.feed_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = feedItems.size

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int ) {
        holder.bindHolder( feedItems[position] )
    }

    class FeedItemViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView ) {

        private val TAG = "FeedItemViewHolder"

        fun bindHolder( feedItem : FeedItem ) {
            itemView.name.text = feedItem.name
            itemView.weight.text = feedItem.weight
            itemView.colorStrip.setBackgroundColor(Color.parseColor(feedItem.bagColor))
        }

    }
}