package com.arash.altafi.englishteachercompose.di

import com.arash.altafi.englishteachercompose.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideApiService(@Named("arashaltafi") retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}
