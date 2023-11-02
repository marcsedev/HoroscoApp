package com.marcsedev.horoscoapp.domain.moel

sealed class HoroscopeInfo(val img: Int, val name: Int) {
    object Aries : HoroscopeInfo()
}
