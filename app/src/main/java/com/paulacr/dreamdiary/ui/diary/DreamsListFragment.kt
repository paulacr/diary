package com.paulacr.dreamdiary.ui.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paulacr.dreamdiary.R
import com.paulacr.dreamdiary.data.model.Dream
import com.paulacr.dreamdiary.ui.ViewState
import com.paulacr.dreamdiary.ui.gone
import com.paulacr.dreamdiary.ui.setupRemoveItemsHelper
import com.paulacr.dreamdiary.ui.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dreams_lists.*
import java.time.LocalDateTime

@AndroidEntryPoint
class DreamsListFragment : Fragment() {

    private val viewModel: DreamsListViewModel by viewModels()
    private var adapter: DreamsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_dreams_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupClickListeners(view)
        observeDreamsList()
        observeDreamsRemoval()
        viewModel.getDreams()

        val localDateTime = LocalDateTime.now()
        monthSelectorView.enableLeftArrow(false)
        monthSelectorView.setupViewPager(
            parentFragmentManager,
            viewLifecycleOwner,
            listOf(localDateTime, localDateTime, localDateTime, localDateTime)
        )
    }

    override fun onResume() {
        super.onResume()
    }

    private fun observeDreamsList() {
        viewModel.dreamsLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Success -> {
                    it.data?.let { dreamsList ->
                        if (dreamsList.isNotEmpty()) {
                            setupDreamsList(dreamsList)
                        } else {
                            showEmptyDreamsList()
                        }
                    }
                }
                is ViewState.Loading -> {
                    showLoading()
                }
            }
        })
    }

    private fun observeDreamsRemoval() {
        viewModel.removeDreamLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    it.data?.let { id ->
                        dismissLoading()
                        adapter?.removeDreamById(id)
                        if (adapter?.itemCount == 0) {
                            showEmptyDreamsList()
                        }
                    }
                }

                is ViewState.Loading -> {
                    showLoadingOnly()
                }

                is ViewState.Error -> {

                }
            }
        }
    }

    private fun setupDreamsList(dreams: List<Dream>) {
        if (adapter == null) {
            adapter = DreamsListAdapter(dreams) {
                expandDreamItemBottomSheet()
            }
            adapter?.setupRemoveItemsHelper(dreamsList) { adapterPosition: Int, dreamId: Long ->
                viewModel.removeDream(dreamId)
            }
            dreamsList.layoutManager = LinearLayoutManager(context)
            dreamsList.adapter = adapter


        } else {
            adapter?.insertDreamsList(dreams)
        }
        showDreamsList()
    }

    private fun setupClickListeners(view: View) {
        val addNewDreamButton = view.findViewById<FloatingActionButton>(R.id.addNewDream)
        addNewDreamButton.setOnClickListener {
            expandDreamItemBottomSheet()
        }
    }

    private fun expandDreamItemBottomSheet() {
        val fragment = DreamItemBottomSheet.newInstance()
        fragment.addNewDreamLiveData.observe(viewLifecycleOwner, {
            //added new item // update recyclerview
            println("Dreams -> dream id ${it.id}")
            if (adapter == null) {
                setupDreamsList(listOf(it))
            } else {
                adapter?.insertNewDream(it)
            }
        })
        fragment.show(parentFragmentManager, "")
    }

    private fun showLoading() {
        viewLoading.visible()
        viewEmptyDreamsList.gone()
        dreamsList.gone()
    }

    private fun dismissLoading() {
        viewLoading.gone()
    }

    private fun showLoadingOnly() {
        viewLoading.visible()
    }

    private fun showEmptyDreamsList() {
        viewLoading.gone()
        viewEmptyDreamsList.visible()
        dreamsList.gone()
    }

    private fun showDreamsList() {
        viewLoading.gone()
        viewEmptyDreamsList.gone()
        dreamsList.visible()
    }
}