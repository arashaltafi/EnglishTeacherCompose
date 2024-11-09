package com.arash.altafi.englishteachercompose.ui.presentation.learn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.arash.altafi.englishteachercompose.data.usecase.LearnUseCase
import com.arash.altafi.englishteachercompose.model.ResponsePaging
import com.arash.altafi.englishteachercompose.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(
    private val useCase: LearnUseCase
) : BaseViewModel() {
    private val _userListState = mutableStateOf<List<ResponsePaging>>(emptyList())
    val userListState: State<List<ResponsePaging>> = _userListState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            useCase().collect { users ->
                _userListState.value = users
            }
        }
    }
}