package com.example.horoscopoapp.core

import com.example.horoscopoapp.data.network.HoroscopeApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://aztro.sameerkumar.website"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    fun provideHoroscopeApi( retrofit: Retrofit) : HoroscopeApi{
        return retrofit.create(HoroscopeApi::class.java)
    }
}
