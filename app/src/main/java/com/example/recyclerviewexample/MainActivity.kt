package com.example.recyclerviewexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MultiAdapter { item ->
            if (item is ListItem.ImageItem)
                Toast.makeText(this, item.toString(), Toast.LENGTH_LONG).show()
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        val list = listOf(
            ListItem.TextItem(1, "Text 1"),
            ListItem.ImageItem(2, android.R.drawable.star_on),
            ListItem.TextItem(3, "Text 1"),
            ListItem.ImageItem(4, android.R.drawable.star_on),
            ListItem.TextItem(5, "Text 1"),
            ListItem.ImageItem(6, android.R.drawable.star_on),
            ListItem.TextItem(7, "Text 1"),
            ListItem.ImageItem(8, android.R.drawable.star_on),
            ListItem.TextItem(9, "Text 1"),
            ListItem.ImageItem(10, android.R.drawable.star_on),
        )

        adapter.submitList(list)

    }
}