package com.example.recyclerviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MultiAdapter(
    private val onItemClick: (ListItem) -> Unit
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(ListDiffCallback()) {

    companion object {
        private const val TYPE_TEXT = 0
        private const val TYPE_IMAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListItem.TextItem -> TYPE_TEXT
            is ListItem.ImageItem -> TYPE_IMAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TEXT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
                TextViewHolder(view)
            }
            TYPE_IMAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_image, parent, false)
                ImageViewHolder(view, onItemClick)
            }
            else -> throw IllegalArgumentException("Unknown type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextViewHolder -> holder.bind(getItem(position) as ListItem.TextItem)
            is ImageViewHolder -> holder.bind(getItem(position) as ListItem.ImageItem)
        }
    }

    class TextViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(android.R.id.text1)
        fun bind(item: ListItem.TextItem) {
            textView.text = item.text
        }
    }

    class ImageViewHolder(itemView: View, val onItemClick: (ListItem) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        fun bind(item: ListItem.ImageItem) {
            imageView.setImageResource(item.imageRes)
            imageView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }


}