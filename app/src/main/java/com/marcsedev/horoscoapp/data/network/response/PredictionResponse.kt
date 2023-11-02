package com.marcsedev.horoscoapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.marcsedev.horoscoapp.domain.model.PredictionModel

data class PredictionResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String,
) {
    //Mapping
    fun toDomain(): PredictionModel {
        return PredictionModel(horoscope = horoscope, sign = sign)
    }
}