package com.arash.altafi.englishteachercompose.data.repository

import com.arash.altafi.englishteachercompose.data.remote.ApiService
import com.arash.altafi.englishteachercompose.model.ResponsePaging
import javax.inject.Inject

class LearnRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getUsers(): List<ResponsePaging> {
        return apiService.getPagingData(1, 20)
    }

}
