package dev.kibet.movetechtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.kibet.movetechtest.data.api.MoveSmsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApisService(retrofit: Retrofit): MoveSmsApi = retrofit.create(MoveSmsApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://sms.movesms.co.ke/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}