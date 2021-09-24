package com.paulacr.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.paulacr.presentation.R

class MonthSelectorFragment : Fragment() {

    companion object {

        fun newInstance(name: String): MonthSelectorFragment {
            val fragment = MonthSelectorFragment()
            val bundle = Bundle()
            bundle.putString("name", name)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var monthText: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_view_month_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        monthText = view.findViewById(R.id.month)
        monthText?.text = arguments?.getString("name")
    }
}