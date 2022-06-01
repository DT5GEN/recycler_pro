package com.dt5gen.recycler_pro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val itemClicked: ((item: String) -> Unit)? = null) :
    RecyclerView.Adapter<ListAdapter.NumberViewHolder>() {

    private val data = (0..35).map { it.toString() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder =
        NumberViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
        )

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.txt.text = data[position]
    }

    override fun getItemCount(): Int = data.size
    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt: TextView = itemView.findViewById(R.id.text)

        init {
            itemView.setOnClickListener {
                itemClicked?.invoke(data[adapterPosition])
            }
        }
    }


}