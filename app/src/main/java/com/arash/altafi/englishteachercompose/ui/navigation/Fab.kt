package com.arash.altafi.englishteachercompose.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun Fab(
    context: Context
) {
    FloatingActionButton(
        onClick = {
            Toast.makeText(context, "FAB clicked", Toast.LENGTH_SHORT).show()
        },
        containerColor = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 0.dp
        )
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}