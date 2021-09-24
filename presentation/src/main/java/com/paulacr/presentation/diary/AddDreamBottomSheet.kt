package com.paulacr.presentation.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.paulacr.domain.Dream
import com.paulacr.presentation.R
import com.paulacr.presentation.ViewState
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class DreamItemBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(dream: Dream): DreamItemBottomSheet {
            val bundle = Bundle()
            val fragment = DreamItemBottomSheet()

            bundle.putSerializable("dream", dream)
            fragment.arguments = bundle

            return fragment
        }

        fun newInstance(): DreamItemBottomSheet {
            return DreamItemBottomSheet()
        }
    }

    private var addNewItemButton: Button? = null
    private val viewModel: AddDreamViewModel by viewModels()
    val addNewDreamLiveData = MutableLiveData<com.paulacr.domain.Dream>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dream_bottom_sheet, container, false)
        addNewItemButton = view.findViewById(R.id.addDreamButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAddDreamClickListener()
        observeAddedNewItem()
        setupViews()
    }

    private fun setupViews() {
        val args = arguments?.get("dream")
        if (args != null) {
            "Save again"
        } else {
            "Add new dream"
        }.apply {
            addNewItemButton?.text = this
        }
    }

    private fun observeAddedNewItem() {
        viewModel.addNewItemLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Success -> it.data?.let { item ->
                    addNewDreamLiveData.value = item
                    dialog?.dismiss()
                }
            }
        })
    }

    private fun setupAddDreamClickListener() {
        addNewItemButton?.setOnClickListener {

            val emojis = listOf(R.drawable.ic_shocked, R.drawable.ic_in_love, R.drawable.ic_happy)

//            viewModel.addNewItem(
//                dateTime = getDateTime(),
//                descriptionText = descriptionAddText.text.toString(),
//                emoji = emojis.random()
//            )
        }
    }

    private fun getDateTime(): LocalDateTime {
        return LocalDateTime.now()
    }
}


