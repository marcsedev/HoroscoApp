package com.marcsedev.horoscoapp.ui.providers

import com.marcsedev.horoscoapp.motherobject.HoroscopeMotherObject
import com.marcsedev.horoscoapp.ui.providers.RandomCardProvider
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RandomCardProviderTest {

    @Test
    fun `getRandomCard should return a random card`() {
        //Given
        val randomCard = RandomCardProvider()

        //When
        val card = randomCard.getLucky()

        //Then
        assertNotNull(card)
    }
}