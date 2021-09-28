package com.paulacr.presentation.diary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulacr.domain.Dream
import com.paulacr.presentation.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddDreamViewModel @Inject constructor(private val dreamUseCase: com.paulacr.domain.DreamUseCase) :
    ViewModel() {

    val addNewItemLiveData = MutableLiveData<ViewState<Dream>>()
    val removeDreamLiveData = MutableLiveData<ViewState<Dream>>()

    fun addNewItem(dateTime: LocalDateTime, descriptionText: String, emoji: Int) {

        addNewItemLiveData.value = ViewState.Loading()
        viewModelScope.launch {
            try {
                val dream = Dream(
                    dateTime = dateTime,
                    description = descriptionText,
                    emoji = emoji
                )
                dreamUseCase(dream)
                addNewItemLiveData.value = ViewState.Success(dream)
            } catch (ex: Exception) {
                addNewItemLiveData.value = ViewState.Error(exception = ex)
            }
        }
    }

    fun removeDream(dreamId: Long) {
        removeDreamLiveData.value = ViewState.Loading()
        viewModelScope.launch {
            try {
                dreamUseCase(dreamId)
                addNewItemLiveData.value = ViewState.Success(null)
            } catch (ex: Exception) {
                addNewItemLiveData.value = ViewState.Error(exception = ex)
            }
        }
    }
}
