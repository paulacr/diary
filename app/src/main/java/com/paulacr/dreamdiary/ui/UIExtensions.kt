package com.paulacr.dreamdiary.ui

import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
   this.visibility = View.VISIBLE
}

fun BaseAdapter<*>.setupRemoveItemsHelper(recyclerView: RecyclerView, onItemRemoved: (position: Int, dreamId: Long)  -> Unit) {

    val itemTouchHelper = object :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewHolder.adapterPosition.run {
                onItemRemoved(this, this@setupRemoveItemsHelper.getItemId(this))
            }
        }
    }

    ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView)
}