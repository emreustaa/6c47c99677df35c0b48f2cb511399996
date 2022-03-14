package com.emreusta.spacechallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreusta.spacechallenge.data.model.response.SpaceResponseModel
import com.emreusta.spacechallenge.data.repository.SpaceRepository
import com.emreusta.spacechallenge.data.repository.SpaceRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val spaceRepository: SpaceRepository
) : ViewModel() {

    private val _stationsLiveData: MutableLiveData<SpaceResponseModel> = MutableLiveData()
    val stationLiveData: LiveData<SpaceResponseModel> = _stationsLiveData

    fun getAllStations() {
        viewModelScope.launch {
            spaceRepository.getAllSpaceDate().collect { response ->
                if (response.isSuccessful) {
                    response.body()?.let { model ->
                        _stationsLiveData.value = model
                    }
                }
            }
        }
    }
}