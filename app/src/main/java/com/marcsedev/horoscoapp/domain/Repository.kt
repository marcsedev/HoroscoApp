package com.marcsedev.horoscoapp.domain

import com.marcsedev.horoscoapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel?
}