package com.marcsedev.horoscoapp.motherobject

import com.marcsedev.horoscoapp.data.network.response.PredictionResponse
import com.marcsedev.horoscoapp.domain.model.HoroscopeInfo.*

object HoroscopeMotherObject {

    val anyResponse = PredictionResponse("date", "prediction", "taurus")

    val horoscopeInfoList = listOf(
        Aries,
        Taurus,
        Gemini,
        Cancer,
        Leo,
        Virgo,
        Libra,
        Scorpio,
        Sagittarius,
        Capricorn,
        Aquarius,
        Pisces
    )

}