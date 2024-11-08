package com.arash.altafi.englishteachercompose.data.repository

import com.arash.altafi.englishteachercompose.data.db.FavoriteUserDao
import com.arash.altafi.englishteachercompose.data.remote.ApiService
import com.arash.altafi.englishteachercompose.model.FavoriteUserEntity
import com.arash.altafi.englishteachercompose.utils.BaseRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val favoriteUserDao: FavoriteUserDao
) : BaseRepository() {
    // Network operations
    fun getUsers() = callApi {
        apiService.getUsers()
    }
    fun getUserDetails(id: Int) = callApi {
        apiService.getUserDetails(id)
    }


    // Room operations
    fun addFavorite(user: FavoriteUserEntity) = callDatabase {
        favoriteUserDao.addFavorite(user)
    }
    fun removeFavorite(user: FavoriteUserEntity) = callDatabase {
        favoriteUserDao.removeFavorite(user)
    }
    fun isFavorite(userId: Int) = callDatabase {
        favoriteUserDao.getFavoriteById(userId) != null
    }
}