package com.paulacr.presentation.diary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.paulacr.domain.Dream
import com.paulacr.presentation.R
import com.paulacr.presentation.ViewState
import com.paulacr.presentation.databinding.FragmentDreamBottomSheetBinding
import com.paulacr.presentation.gone
import com.paulacr.presentation.visible
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class AddDreamBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(dream: Dream): AddDreamBottomSheet {
            val bundle = Bundle()
            val fragment = AddDreamBottomSheet()

            bundle.putSerializable("dream", dream)
            fragment.arguments = bundle

            return fragment
        }

        fun newInstance(): AddDreamBottomSheet {
            return AddDreamBottomSheet()
        }
    }

    private val viewModel: AddDreamViewModel by viewModels()
    private lateinit var binding: FragmentDreamBottomSheetBinding
    private var dream: Dream? = null
    val addNewDreamLiveData = MutableLiveData<Dream>()
    val removeDreamLiveData = MutableLiveData<Dream>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDreamBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAddDreamClickListener()
        observeAddedNewItem()
        setupViews()
    }

    private fun setupViews() {
        dream = arguments?.get("dream") as Dream?
        with(binding) {
            dream?.let { dream ->
                deleteButton.visible()
                descriptionAddText.setText(dream.description)
                addDreamButton.text = "Save again"
            } ?: run {
                deleteButton.gone()
                addDreamButton.text = "Add new Dream"
            }
        }

        binding.deleteButton.setOnClickListener {
            dream?.id?.let { id -> viewModel.removeDream(id) }
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

        viewModel.removeDreamLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Success -> it.data?.let { item ->
                    removeDreamLiveData.value = item
                    dialog?.dismiss()
                }
            }
        })
    }

    private fun setupAddDreamClickListener() {
        binding.addDreamButton.setOnClickListener {

            val emojis = listOf(R.drawable.ic_shocked, R.drawable.ic_in_love, R.drawable.ic_happy)

            viewModel.addNewItem(
                dateTime = getDateTime(),
                descriptionText = binding.descriptionAddText.text.toString(),
                emoji = emojis.random()
            )
        }
    }

    private fun getDateTime(): LocalDateTime {
        return LocalDateTime.now()
    }
}
