package com.googleio.publicapi.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.googleio.publicapi.domain.models.Resource
import com.googleio.publicapi.domain.repository.CategoryRepository
import com.googleio.publicapi.domain.usecases.FetchCategoryUseCase
import com.googleio.publicapi.domain.usecases.FetchEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicAPIViewModel @Inject constructor(
    private val  fetchEntryUseCase: FetchEntryUseCase,
    private val  fetchCategoryUseCase: FetchCategoryUseCase
) : ViewModel() {
    private val _clickedAPI : MutableLiveData<String> = MutableLiveData("")
    val clickedAPI : LiveData<String> = _clickedAPI

    private val _entryDataList : MutableLiveData<EntryState> = MutableLiveData(EntryState.StandBy)
    val entryDataList : LiveData<EntryState> = _entryDataList


    init{
        getEntryData()
    }

    private fun getEntryData() = viewModelScope.launch {
        when (val entryDataResource = fetchEntryUseCase()) {
            is Resource.Success -> {
                if(entryDataResource.data?.entries?.size == 0) {
                     _entryDataList.value = EntryState.Empty(entryDataResource.data)
                } else {
                    _entryDataList.value = EntryState.Success(entryDataResource.data)
                }
            }
            is Resource.Error -> {
                _entryDataList.value = EntryState.Failure(entryDataResource.message ?: "Unknown Error Occurred")
            }
            is Resource.Loading -> {
                _entryDataList.value = EntryState.Loading
            }
        }
    }


    fun updateClickedAPI(apiLink : String) {
        _clickedAPI.value = apiLink
    }

    sealed class EntryState {
        data class Success<T>(val data : T) : EntryState()

        data class Empty<T>(val data: T) : EntryState()

        data class Failure(val message: String) : EntryState()

        object Loading : EntryState()

        object StandBy : EntryState()
    }
}