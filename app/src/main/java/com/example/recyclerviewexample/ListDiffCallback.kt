package com.example.recyclerviewexample

import androidx.recyclerview.widget.DiffUtil

class ListDiffCallback : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean {
        return when {
            oldItem is ListItem.TextItem && newItem is ListItem.TextItem -> oldItem.id == newItem.id
            oldItem is ListItem.ImageItem && newItem is ListItem.ImageItem -> oldItem.id == newItem.id
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: ListItem,
        newItem: ListItem
    ): Boolean {
        return oldItem == newItem
    }
}