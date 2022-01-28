package com.example.testandroidapp.di

import com.example.testandroidapp.network.ValuteApiClient
import com.example.testandroidapp.network.ValuteApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofitClient(): ValuteApiInterface {
        return ValuteApiClient.getClient()
    }
}