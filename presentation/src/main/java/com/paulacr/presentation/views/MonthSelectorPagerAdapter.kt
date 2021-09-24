package com.paulacr.presentation.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.adapter.FragmentStateAdapter

class MonthSelectorPagerAdapter(fm: FragmentManager, lifecycle: LifecycleOwner) : FragmentStateAdapter(fm, lifecycle.lifecycle) {

    private val fragments = mutableListOf<Fragment>()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    fun addFragmentsAtOnce(frags: List<Fragment>) {
        fragments.addAll(frags)
    }
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}