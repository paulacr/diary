package com.paulacr.presentation.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paulacr.domain.Dream
import com.paulacr.presentation.*
import com.paulacr.presentation.databinding.FragmentDreamsListsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class DreamsListFragment : Fragment() {

    private val viewModel: DreamsListViewModel by viewModels()
    private var dreamsListAdapter: DreamsListAdapter? = null
    private lateinit var binding: FragmentDreamsListsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDreamsListsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupClickListeners(view)
        observeDreamsList()
        observeDreamsRemoval()
        viewModel.getDreams()
    }

    private fun observeDreamsList() {
        observe(viewModel.dreamsLiveData) {
            when (it) {
                is ViewState.Success -> {
                    it.data?.let { dreamsList ->
                        if (dreamsList.isNotEmpty()) {
                            setupDreamsList(dreamsList)
                            setupMonthSelector(dreamsList)
                        } else {
                            showEmptyDreamsList()
                        }
                    }
                }
                is ViewState.Loading -> {
                    showLoading()
                }
            }
        }
    }

    private fun setupMonthSelector(dreamsList: List<Dream>) {
        val datetimes: List<LocalDateTime> = dreamsList.map {
            it.dateTime
        }

        binding.monthSelectorView.setupViewPager(
            parentFragmentManager,
            viewLifecycleOwner,
            datetimes
        )
    }

    private fun observeDreamsRemoval() {
        viewModel.removeDreamLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    it.data?.let { id ->
                        dismissLoading()
                        dreamsListAdapter?.removeDreamById(id)
                        if (dreamsListAdapter?.itemCount == 0) {
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
        if (dreamsListAdapter == null) {
            dreamsListAdapter = DreamsListAdapter(dreams) {
                expandDreamItemBottomSheet(it)
            }
            with(binding.dreamsList) {
                dreamsListAdapter.setupRemoveItemsHelper(this) { adapterPosition: Int, dreamId: Long ->
                    viewModel.removeDream(dreamId)
                }
                layoutManager = LinearLayoutManager(context)
                adapter = dreamsListAdapter
            }
        } else {
            dreamsListAdapter?.insertDreamsList(dreams)
        }
        showDreamsList()
    }

    private fun setupClickListeners(view: View) {
        val addNewDreamButton = view.findViewById<FloatingActionButton>(R.id.addNewDream)
        addNewDreamButton.setOnClickListener {
            expandDreamItemBottomSheet()
        }
    }

    private fun expandDreamItemBottomSheet(dream: Dream? = null) {

        val fragment: AddDreamBottomSheet = if (dream == null) {
            AddDreamBottomSheet.newInstance()
        } else {
            AddDreamBottomSheet.newInstance(dream)
        }
        observe(fragment.addNewDreamLiveData) {
            if (dreamsListAdapter == null) {
                setupDreamsList(listOf(it))
            } else {
                dreamsListAdapter?.insertNewDream(it)
            }
        }

        observe(fragment.removeDreamLiveData) {
            it.id?.let { dreamId ->
                dreamsListAdapter?.removeDreamById(dreamId)
            }
        }

        fragment.show(parentFragmentManager, "")
    }

    private fun showLoading() {
        with(binding) {
            viewLoading.visible()
            viewEmptyDreamsList.gone()
            dreamsList.gone()
        }
    }

    private fun dismissLoading() {
        binding.viewLoading.gone()
    }

    private fun showLoadingOnly() {
        binding.viewLoading.visible()
    }

    private fun showEmptyDreamsList() {
        with(binding) {
            viewLoading.gone()
            viewEmptyDreamsList.visible()
            dreamsList.gone()
            monthSelectorView.gone()
        }
    }

    private fun showDreamsList() {
        with(binding) {
            viewLoading.gone()
            viewEmptyDreamsList.gone()
            dreamsList.visible()
            monthSelectorView.visible()
        }
    }
}
