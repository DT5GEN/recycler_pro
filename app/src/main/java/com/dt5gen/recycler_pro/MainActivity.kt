package com.dt5gen.recycler_pro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: RecyclerView = findViewById(R.id.list)

        val helper = ItemTouchHelper(ItemTouchHelperCallback(

            {
                adapter.itemRemoved(it)
                adapter.notifyItemRemoved(it)
            }, {

                    from, to ->
                with(adapter) {
                    itemsMoved(from, to)
                    notifyItemMoved(from, to)
                    notifyItemChanged(from)
                    notifyItemChanged(to)

                }

            }

        ))

        val data = mutableListOf(
            HeaderItem("id1", "Some"),
            NumberItem("id2", "1"),
            HeaderItem("id3", "Some"),
            NumberItem("id4", "2"),
            HeaderItem("id5", "Some Again"),
            NumberItem("id6", "3"),
            NumberItem("id7", "4"),
            ImageItem("id8", R.drawable.smile),
            HeaderItem("id9", "Some Again"),
            NumberItem("id10", "5"),
            ImageItem("id11", R.drawable.smile),
            NumberItem("id12", "6"),
            ImageItem("id13", R.drawable.smile),
            NumberItem("id14", "7"),
            HeaderItem("id15", "Some Again"),
            ImageItem("id16", R.drawable.smile),
            NumberItem("id17", "8"),
            ImageItem("id18", R.drawable.smile),
            NumberItem("id19", "9"),
            ImageItem("id21", R.drawable.smile)
        )

        adapter = ListAdapter({
            Snackbar.make(list, it, Snackbar.LENGTH_SHORT).show()
        },
            {
                Snackbar.make(list, it.img.toString(), Snackbar.LENGTH_SHORT).show()
            },
            {
                helper.startDrag(it)
            },
            {

                val copy = ArrayList(data)
                copy.removeAt(it)
                adapter.submitList(copy)
            }

        ).apply {
            submitList(data)
            notifyDataSetChanged()
        }

        list.adapter = adapter


        helper.attachToRecyclerView(list)

        list.addItemDecoration(ListItemDecorator())


    }
}