package com.dt5gen.recycler_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: RecyclerView = findViewById(R.id.list)

        list.adapter = ListAdapter ({
            Snackbar.make(list, it, Snackbar.LENGTH_SHORT).show()
        },
            {
                Snackbar.make(list, it.img.toString(), Snackbar.LENGTH_SHORT).show()
            }).apply {
           setData(
               listOf(
                   HeaderItem("Some"),
                   NumberItem("1"),
                   HeaderItem("Some"),
                   NumberItem("2"),
                   HeaderItem("Some Again"),
                   NumberItem("3"),
                   NumberItem("4"),
                   ImageItem(R.drawable.smile),
                   HeaderItem("Some Again"),
                   NumberItem("5"),
                   ImageItem(R.drawable.smile),
                   NumberItem("6"),
                   ImageItem(R.drawable.smile),
                   NumberItem("7"),
                   HeaderItem("Some Again"),
                   ImageItem(R.drawable.smile),
                   NumberItem("8"),
                   ImageItem(R.drawable.smile),
                   NumberItem("9"),
                   ImageItem(R.drawable.smile)
               )
           )
        }


    }
}