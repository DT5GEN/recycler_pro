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

        list.adapter = ListAdapter {
            Snackbar.make(list, it, Snackbar.LENGTH_SHORT).show()
        }


    }
}