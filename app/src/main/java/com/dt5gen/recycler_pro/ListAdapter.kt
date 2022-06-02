package com.dt5gen.recycler_pro

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListAdapter(
    private val numberItemClicked: ((item: String) -> Unit)? = null,
    private val imageLongClicked: ((item: ImageItem) -> Unit)? = null,
    private val itemDragged: ((viewHolder: RecyclerView.ViewHolder) -> Unit)? = null
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
                with(holder) {

                    txt.text = item.textNumberItem

                    up.visibleIf { position != 0 }
                    down.visibleIf { position != data.size - 1 }

                }
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

    fun itemRemoved(position: Int) {
        data.removeAt(position)
    }

    fun itemsMoved(from: Int, to: Int) {
        Collections.swap(data, from, to)
         }

    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txt: TextView = itemView.findViewById(R.id.text)


        val up: ImageView = itemView.findViewById(R.id.up)

        val down: ImageView = itemView.findViewById(R.id.down)

        init {
            itemView.setOnClickListener {

                (data[adapterPosition] as? NumberItem)?.let {
                    numberItemClicked?.invoke(it.textNumberItem)
                }
            }

            itemView.findViewById<ImageView>(R.id.delete).setOnClickListener {
                data.removeAt(adapterPosition)
                //  notifyDataSetChanged() // удаление без красоты (анимации )
                notifyItemRemoved(adapterPosition)
            }



            itemView.findViewById<ImageView>(R.id.drag).setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    itemDragged?.invoke(this)
                }
                false
            }




            itemView.findViewById<ImageView>(R.id.addItem).setOnClickListener {
                data.add(adapterPosition + 1, NumberItem(UUID.randomUUID().toString()))
                notifyItemInserted(adapterPosition + 1)
            }

            itemView.findViewById<ImageView>(R.id.update).setOnClickListener {
                data[adapterPosition] = NumberItem(UUID.randomUUID().toString())
                notifyItemChanged(adapterPosition)
            }

            up.setOnClickListener {
                Collections.swap(data, adapterPosition, adapterPosition - 1)

                up.visibleIf { adapterPosition - 1 != 0 }
                notifyItemMoved(adapterPosition, adapterPosition - 1)
                notifyItemChanged(adapterPosition)

            }

            down.setOnClickListener {
                Collections.swap(data, adapterPosition, adapterPosition + 1)
                down.visibleIf { adapterPosition + 1 != data.size - 1 }

                notifyItemMoved(adapterPosition, adapterPosition + 1)
                notifyItemChanged(adapterPosition)
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


