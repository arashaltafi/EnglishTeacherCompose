package com.arash.altafi.englishteachercompose.data.usecase

import com.arash.altafi.englishteachercompose.data.repository.LearnRepository
import com.arash.altafi.englishteachercompose.model.ResponsePaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LearnUseCase @Inject constructor(
    private val repository: LearnRepository
) {
    operator fun invoke(): Flow<List<ResponsePaging>> = flow {
        emit(repository.getUsers())
    }.catch { e -> emit(emptyList()) }
}