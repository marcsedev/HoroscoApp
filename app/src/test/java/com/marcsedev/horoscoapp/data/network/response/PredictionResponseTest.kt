package com.marcsedev.horoscoapp.data.network.response

import com.marcsedev.horoscoapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PredictionResponseTest {

    @Test
    fun `toDomain should return a correct PredictionModel`() {
        //Given
        val horoscopeResponse = anyResponse

        //When
        val predictionModel=horoscopeResponse.toDomain()

        //Then
        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope

    }
}