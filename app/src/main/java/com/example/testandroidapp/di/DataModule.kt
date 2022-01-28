package com.example.testandroidapp.di

import android.content.Context
import com.example.testandroidapp.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Repository {
        return Repository(context)
    }
}