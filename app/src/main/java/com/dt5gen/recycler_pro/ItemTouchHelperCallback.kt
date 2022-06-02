package com.dt5gen.recycler_pro

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback (
    val onItemSwiped : (position: Int) -> Unit,
    val onItemMoved : (from: Int, to: Int) -> Unit
): ItemTouchHelper.Callback(){

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val drag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipe = ItemTouchHelper.START or ItemTouchHelper.END

        return makeMovementFlags(drag, swipe)
    }

    override fun isItemViewSwipeEnabled(): Boolean = true


    override fun isLongPressDragEnabled(): Boolean = true

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {

        val from = viewHolder.adapterPosition
        val to = target.adapterPosition

        onItemMoved(from, to)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    val position = viewHolder.adapterPosition
        onItemSwiped(position)
    }


    @SuppressLint("ResourceAsColor")
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.itemView?.setBackgroundColor(R.color.touch)
    }


    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.background = null

    }


}