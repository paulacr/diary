package com.paulacr.presentation.diary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulacr.domain.Dream
import com.paulacr.domain.DreamUseCase
import com.paulacr.presentation.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DreamsListViewModel @Inject constructor(private val dreamUseCase: DreamUseCase) : ViewModel() {

    val dreamsLiveData = MutableLiveData<ViewState<List<Dream>>>()
    val removeDreamLiveData = MutableLiveData<ViewState<Long>>()

    fun getDreams() {
        dreamsLiveData.value = ViewState.Loading()
        viewModelScope.launch {
            try {
                val dreams = dreamUseCase.invoke()
                dreamsLiveData.value = ViewState.Success(dreams)
            } catch (exception: Exception) {
                println()
            }
        }
    }

    fun removeDream(dreamId: Long) {
        viewModelScope.launch {
            try {
                dreamUseCase.invoke(dreamId)
                removeDreamLiveData.value = ViewState.Success(dreamId)
            } catch (exception: Exception) {
                println()
            }
        }
    }

    fun changeMonth() {

    }
}