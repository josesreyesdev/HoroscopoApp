package com.example.horoscopoapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopoapp.core.network.ResultType
import com.example.horoscopoapp.domain.GetHoroscopeUseCase
import com.example.horoscopoapp.domain.dto.HoroscopeDTO
import com.example.horoscopoapp.domain.model.HoroscopeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DetailUIState {
    object Loading : DetailUIState()
    data class Success(val horoscopeModel: HoroscopeModel) : DetailUIState()
    data class Error( val msg: String) : DetailUIState()
}

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase): ViewModel() {

    private val _uiState = MutableStateFlow<DetailUIState> (DetailUIState.Loading)
    val uiState: StateFlow<DetailUIState> = _uiState

    fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase(HoroscopeDTO(sign = "aries"))
                .collect{ result ->
                    when (result) {
                        is ResultType.Error -> {
                            _uiState.value = DetailUIState.Error(result.msg ?: "Mensaje de Error")
                        }
                        is ResultType.Success -> {
                            _uiState.value = DetailUIState.Success(result.data!!)
                        }
                    }
                }
        }

    }
}