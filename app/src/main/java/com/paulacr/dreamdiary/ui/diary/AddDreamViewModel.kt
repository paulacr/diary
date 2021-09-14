package com.paulacr.dreamdiary.ui.diary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulacr.dreamdiary.data.model.Dream
import com.paulacr.dreamdiary.data.repository.DreamListRepository
import com.paulacr.dreamdiary.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddDreamViewModel @Inject constructor(private val dreamListRepository: DreamListRepository) :
    ViewModel() {

    val addNewItemLiveData = MutableLiveData<ViewState<Dream>>()

    fun addNewItem(dateTime: LocalDateTime, descriptionText: String, emoji: Int) {

        viewModelScope.launch {
            val dreamId: Long? = dreamListRepository.getDreams()?.sortedBy {
                it.id
            }?.lastOrNull()?.id

            val dream = Dream(
                id = dreamId?.plus(1)?: 0,
                dateTime = dateTime,
                description = descriptionText,
                emoji = emoji
            )

            try {
                dreamListRepository.addDream(dream)
                addNewItemLiveData.value = ViewState.Success(dream)
            } catch (ex: Exception) {
                print(ex)
            }
        }
    }
}
