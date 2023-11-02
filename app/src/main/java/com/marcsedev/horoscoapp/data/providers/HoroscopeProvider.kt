package com.marcsedev.horoscoapp.data.providers

import com.marcsedev.horoscoapp.domain.moel.HoroscopeInfo
import com.marcsedev.horoscoapp.domain.moel.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
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
}