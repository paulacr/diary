package com.paulacr.dreamdiary.ui.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulacr.dreamdiary.data.model.Dream
import com.paulacr.dreamdiary.data.repository.DreamListRepository
import com.paulacr.dreamdiary.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamsListViewModel @Inject constructor(private val dreamsRepository: DreamListRepository) : ViewModel() {

    val dreamsLiveData = MutableLiveData<ViewState<List<Dream>>>()
    val removeDreamLiveData = MutableLiveData<ViewState<Long>>()

    fun getDreams() {
        dreamsLiveData.value = ViewState.Loading()
        viewModelScope.launch {
            try {
                val dreams = dreamsRepository.getDreams()
                dreamsLiveData.value = ViewState.Success(dreams)
            } catch (exception: Exception) {
                println()
            }
        }
    }

    fun removeDream(dreamId: Long) {
        viewModelScope.launch {
            try {
                dreamsRepository.removeDreamById(dreamId)
                removeDreamLiveData.value = ViewState.Success(dreamId)
            } catch (exception: Exception) {
                println()
            }
        }
    }

    fun changeMonth() {

    }
}