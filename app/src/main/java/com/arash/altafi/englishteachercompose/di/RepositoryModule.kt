package com.arash.altafi.englishteachercompose.di

import androidx.datastore.preferences.core.Preferences
import com.arash.altafi.englishteachercompose.data.db.FavoriteUserDao
import com.arash.altafi.englishteachercompose.data.remote.ApiService
import com.arash.altafi.englishteachercompose.data.repository.DataStoreRepository
import com.arash.altafi.englishteachercompose.data.repository.LearnRepository
import com.arash.altafi.englishteachercompose.data.repository.UserRepository
import com.arash.altafi.englishteachercompose.utils.EncryptionUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        apiService: ApiService,
        favoriteUserDao: FavoriteUserDao
    ) = UserRepository(apiService, favoriteUserDao)

    @Singleton
    @Provides
    fun provideLearnRepository(
        apiService: ApiService,
    ) = LearnRepository(apiService)

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        dataStore: androidx.datastore.core.DataStore<Preferences>,
        encryptionUtils: EncryptionUtils
    ) = DataStoreRepository(dataStore, encryptionUtils)

}