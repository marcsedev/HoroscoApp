package com.marcsedev.horoscoapp.ui.horoscope

import com.marcsedev.horoscoapp.data.providers.HoroscopeProvider
import com.marcsedev.horoscoapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.kotlintest.inspectors.forExactly
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewModel is created then horoscopes are loaded`() {
        every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList
        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscope = viewModel.horoscope.value

        assertTrue(horoscope.isNotEmpty())
    }
}

