package com.example.recyclerviewexample

sealed class ListItem {
    data class TextItem(val id: Int, val text: String) : ListItem()
    data class ImageItem(val id: Int, val imageRes: Int) : ListItem()
}