package com.example.horoscopoapp.domain

import com.example.horoscopoapp.core.network.ResultType
import com.example.horoscopoapp.domain.dto.HoroscopeDTO
import com.example.horoscopoapp.domain.model.HoroscopeModel
import com.example.horoscopoapp.repository.HoroscopeRepository
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val horoscopeRepository: HoroscopeRepository){

    // Esta funcion automaticamente se invoka al ser llamado
    //operator fun invoke(horoscopeDTO: HoroscopeDTO) : Flow<ResultType<HoroscopeModel>> = horoscopeRepository.getHoroscope(horoscopeDTO)
    suspend operator fun invoke(horoscopeDTO: HoroscopeDTO) : ResultType<HoroscopeModel> = horoscopeRepository.getHoroscope(horoscopeDTO)
}