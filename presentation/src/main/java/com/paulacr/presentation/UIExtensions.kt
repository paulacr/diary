package com.paulacr.presentation

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun ViewBinding.gone() {
    this.root.visibility = View.GONE
}

fun ViewBinding.visible() {
    this.root.visibility = View.VISIBLE
}

fun BaseAdapter<BaseViewHolder<*>>?.setupRemoveItemsHelper(recyclerView: RecyclerView, onItemRemoved: (position: Int, dreamId: Long) -> Unit) {

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
                onItemRemoved(this, this@setupRemoveItemsHelper!!.getItemId(this))
            }
        }
    }

    ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView)
}

fun <T> Fragment.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, block)
}
