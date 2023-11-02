package com.marcsedev.horoscoapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.marcsedev.horoscoapp.data.providers.HoroscopeProvider
import com.marcsedev.horoscoapp.domain.moel.HoroscopeInfo
import com.marcsedev.horoscoapp.domain.moel.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(private val horoscopeProvider: HoroscopeProvider) :
    ViewModel() {

    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    init {
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }
}