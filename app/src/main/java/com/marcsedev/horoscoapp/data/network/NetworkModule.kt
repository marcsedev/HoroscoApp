package com.marcsedev.horoscoapp.data.network

import com.marcsedev.horoscoapp.BuildConfig.BASE_URL
import com.marcsedev.horoscoapp.data.RepositoryImp
import com.marcsedev.horoscoapp.data.core.interceptors.AuthInterceptor
import com.marcsedev.horoscoapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(auhInterceptor: AuthInterceptor): OkHttpClient {

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(auhInterceptor)
            .build()
    }

    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)

    }

    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository {
        return RepositoryImp(apiService)
    }
}