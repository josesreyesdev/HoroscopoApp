package com.example.horoscopoapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopoapp.data.network.model.HoroscopeResponse
import com.example.horoscopoapp.domain.GetHoroscopeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DetailUIState {
    object Loading : DetailUIState()
    data class Success(val horoscopeResponse: HoroscopeResponse) : DetailUIState()
    data class Error( val msg: String) : DetailUIState()
}

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase): ViewModel() {

    private val _uiState = MutableStateFlow<DetailUIState> (DetailUIState.Loading)
    val uiState: StateFlow<DetailUIState> = _uiState

    fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase()
                .collect{ horoscope ->
                    if ( horoscope != null) {
                        _uiState.value = DetailUIState.Success(horoscope)
                    }
                }
        }

    }
}