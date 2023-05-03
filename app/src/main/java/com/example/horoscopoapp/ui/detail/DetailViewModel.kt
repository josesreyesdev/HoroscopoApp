package com.example.horoscopoapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopoapp.core.network.ResultType
import com.example.horoscopoapp.domain.GetHoroscopeUseCase
import com.example.horoscopoapp.domain.dto.HoroscopeDTO
import com.example.horoscopoapp.domain.model.HoroscopeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    /* Funcion para flows*/
    /*fun getHoroscope() {
        viewModelScope.launch {
            getHoroscopeUseCase(horoscopeDTO = HoroscopeDTO(sign = "aries"))
                .collect{ result ->
                    when (result) {
                        is ResultType.Error -> {
                            /*val error = when (result.errorType) {
                                ErrorType.BadRequest -> "Mensaje Generico"
                                is ErrorType.ExceptionError -> "Mensaje Generico"
                                ErrorType.Forbidden -> "Mensaje Generico"
                                ErrorType.InternalServerError -> "Mensaje generico"
                                ErrorType.InvalidData -> "Mensaje Generico"
                                is ErrorType.UncontrolledError -> "Mensaje Generico"
                            } */
                            _uiState.value = DetailUIState.Error("error")
                        }
                        is ResultType.Success -> {
                            _uiState.value = DetailUIState.Success(result.data!!)
                        }
                    }
                }
        }

    } */

    /* Funcion para coroutines */
    fun getHoroscope(horoscope: String) {
        //Main -> UI
        //IO -> room, retrofit ...
        //Default -> Procesos largos
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = getHoroscopeUseCase(HoroscopeDTO(horoscope))) {
                is ResultType.Error -> _uiState.value = DetailUIState.Error(msg = "Error")
                is ResultType.Success -> {
                    if (response.data != null) {
                        _uiState.value = DetailUIState.Success(horoscopeModel = response.data)
                    }
                }
            }
            /*
            //Cambiar de hilo dentro de una corrutina con:
            //withContext(Dispatchers.Default) {}

            //Corrutinas cuando quiero las tres respuestas luego continuar con awaitAll( async{call1()}, async{calln()})
            val response = async { getHoroscopeUseCase(HoroscopeDTO("aries")) }
            val response1 = async { getHoroscopeUseCase(HoroscopeDTO("aries")) }
            val response2 = async { getHoroscopeUseCase(HoroscopeDTO("aries")) }
            response.await()

            val list: List<ResultType<HoroscopeModel>> = awaitAll(
                async { getHoroscopeUseCase(HoroscopeDTO("aries")) },
                async { getHoroscopeUseCase(HoroscopeDTO("virgo")) },
                async { getHoroscopeUseCase(HoroscopeDTO("capricornio")) },
                async { getHoroscopeUseCase(HoroscopeDTO("aries")) },
            )

            //Cancelar una corrutina
            myJob.cancel()
            */
        }
    }
}