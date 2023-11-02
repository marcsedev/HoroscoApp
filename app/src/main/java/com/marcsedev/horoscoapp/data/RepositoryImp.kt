package com.marcsedev.horoscoapp.data

import android.util.Log
import com.marcsedev.horoscoapp.data.network.HoroscopeApiService
import com.marcsedev.horoscoapp.domain.Repository
import com.marcsedev.horoscoapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val apiService: HoroscopeApiService) : Repository {

    override suspend fun getPrediction(sign: String): PredictionModel? {
        //call retrofit
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Failure", "failure, error ${it.message}") }

        return null
    }
}
