package com.arash.altafi.englishteachercompose.data.remote

import com.arash.altafi.englishteachercompose.model.ResponseUsers
import com.arash.altafi.englishteachercompose.utils.BaseService
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response

interface ApiService: BaseService {
    @GET("users")
    suspend fun getUsers(
    ): Response<List<ResponseUsers>>

    @GET("users/{id}")
    suspend fun getUserDetails(
        @Path("id") id: Int
    ): Response<ResponseUsers>
}
