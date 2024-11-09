package com.arash.altafi.englishteachercompose.ui.presentation.learn

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LearnScreen(navController: NavController) {
    val viewModel: LearnViewModel = hiltViewModel()

    val users = viewModel.userListState.value

    LazyColumn {
        items(users.size) { index ->
            val user = users[index]
            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = user.name,
            )
        }
    }
}