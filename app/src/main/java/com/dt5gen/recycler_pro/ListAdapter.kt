package com.dt5gen.recycler_pro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val numberItemClicked: ((item: String) -> Unit)? = null,
    private val imageLongClicked: ((item: ImageItem) -> Unit)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {

        private const val TYPE_NUMBER = 1
        private const val TYPE_IMAGE = 2
        private const val TYPE_HEADER = 3

    }

    private val data = mutableListOf<AdapterItem>()

    fun setData(list: Collection<AdapterItem>) {
        data.apply {
            clear()
            addAll(list)
        }
    }

    override fun getItemViewType(position: Int): Int = when (data[position]) {
        is NumberItem -> TYPE_NUMBER
        is ImageItem -> TYPE_IMAGE
        is HeaderItem -> TYPE_HEADER
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_NUMBER -> NumberViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)
            )

            TYPE_IMAGE -> ImageViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            )

            TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            )

            else -> throw IllegalStateException("Всё пропало")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is NumberViewHolder -> {
                val item = data[position] as NumberItem
                holder.txt.text = item.textNumberItem
            }
            is ImageViewHolder -> {
                val item = data[position] as ImageItem
                holder.imageItem.setImageResource(item.img)
            }
            is HeaderViewHolder -> {
                val item = data[position] as HeaderItem
                holder.header.text = item.headerItem
            }
        }

    }

    override fun getItemCount(): Int = data.size


    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt: TextView = itemView.findViewById(R.id.text)

        init {
            itemView.setOnClickListener {

                (data[adapterPosition] as? NumberItem)?.let {
                    numberItemClicked?.invoke(it.textNumberItem)
                }
            }
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var header: TextView = itemView.findViewById(R.id.header_title)


    }


    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageItem: ImageView = itemView.findViewById(R.id.image)

        init {
            imageItem.setOnLongClickListener {

                (data[adapterPosition] as? ImageItem)?.let {
                    imageLongClicked?.invoke(it)
                }
                true
            }
        }
    }
}


