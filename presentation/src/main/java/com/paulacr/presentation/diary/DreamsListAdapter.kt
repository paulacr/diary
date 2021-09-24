package com.paulacr.presentation.diary

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.paulacr.domain.Dream
import com.paulacr.presentation.BaseAdapter
import com.paulacr.presentation.BaseViewHolder
import com.paulacr.presentation.R

class DreamsListAdapter(
    private val items: List<Dream>,
    val selectedItemCallback: (item: Dream) -> Unit
) : BaseAdapter<BaseViewHolder<*>>() {

    private val dreamItems = items.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_dream_diary_list, parent, false)
        return DreamViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val viewHolder = holder as DreamViewHolder
        val dreamItem = dreamItems[position]
        viewHolder.bind(dreamItem)
        viewHolder.itemView.setOnClickListener { selectedItemCallback(dreamItem) }
    }

    override fun getItemCount(): Int {
        return dreamItems.size
    }

    class DreamViewHolder(private val viewItem: View) :
        BaseViewHolder<Dream>(viewItem) {
        override fun bind(item: com.paulacr.domain.Dream?) {
            val day = viewItem.findViewById<TextView>(R.id.dayText)
            val dayOfWeek = viewItem.findViewById<TextView>(R.id.dayOfWeekText)
            val month = viewItem.findViewById<TextView>(R.id.monthText)
            val description = viewItem.findViewById<TextView>(R.id.descriptionText)
            val humor = viewItem.findViewById<ImageView>(R.id.humorIcon)
            item?.let {
                val datetime = it.dateTime
                day.text = datetime.dayOfMonth.toString()
                dayOfWeek.text = datetime.dayOfWeek.name.capitalize()
                month.text = datetime.month.name.takeFirstsLetters()
                description.text = it.description
                humor.setImageResource(it.emoji ?: 0)
            }
        }

        private fun String.takeFirstsLetters(): String {
            return this.take(3)
                .capitalize()
        }

        private fun String.capitalize(): String {
            return this.lowercase()
                .replaceFirstChar { it.uppercase() }
        }
    }

    fun insertNewDream(dream: com.paulacr.domain.Dream) {
        dreamItems.add(dream)
        notifyItemInserted(itemCount)
    }

    fun insertDreamsList(dreams: List<com.paulacr.domain.Dream>) {
        dreamItems.addAll(dreams)
        notifyItemRangeChanged(itemCount, dreamItems.size, dreams)
    }

    private fun removeDream(position: Int) {
        dreamItems.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeDreamById(dreamId: Long) {
        val dreamToRemove = dreamItems.find {
            it.id == dreamId
        }
        val indexToRemove = dreamItems.indexOf(dreamToRemove)
        removeDream(indexToRemove)
    }

    override fun getItemId(position: Int): Long {
        return dreamItems[position].id ?: 0
    }
}
