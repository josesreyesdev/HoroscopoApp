package com.example.horoscopoapp.domain

import com.example.horoscopoapp.core.network.ResultType
import com.example.horoscopoapp.domain.dto.HoroscopeDTO
import com.example.horoscopoapp.domain.model.HoroscopeModel
import com.example.horoscopoapp.repository.HoroscopeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val horoscopeRepository: HoroscopeRepository){

    // Esta funcion automaticamente se invoka al ser llamado
     operator fun invoke(horoscopeDTO: HoroscopeDTO) : Flow<ResultType<HoroscopeModel>> = horoscopeRepository.getHoroscope(horoscopeDTO)
}