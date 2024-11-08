package com.arash.altafi.englishteachercompose.ui.navigation

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.arash.altafi.englishteachercompose.R
import com.arash.altafi.englishteachercompose.sharedViewModel.DataStoreViewModel
import com.arash.altafi.englishteachercompose.utils.Constant

@Composable
fun TopBar(
    navController: NavController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    isHomeScreen: Boolean,
    dataStoreViewModel: DataStoreViewModel,
    context: Context,
    theme: String?,
    onNavigationItemSelected: (Int) -> Unit
) {
    val activity = (context as? Activity)

    var doubleBackToExitPressedOnce by remember { mutableStateOf(false) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    @OptIn(ExperimentalMaterial3Api::class)
    TopAppBar(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp)),
        title = {
            Text("اپلیکیشن تست")
        },
        navigationIcon = {
            Row {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            Row {
                IconButton(
                    onClick = {
                        dataStoreViewModel.changeTheme()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = if (theme == "dark") R.drawable.round_light_mode_24 else R.drawable.round_dark_mode_24),
                        contentDescription = if (theme == "dark") "Switch to Light Theme" else "Switch to Dark Theme",
                        tint = Color.White
                    )
                }
                if (!isHomeScreen) {
                    IconButton(
                        onClick = {
                            if (currentDestination !in Constant.AllowRouteToShowBottomBar) {
                                navController.popBackStack()
                            } else if (navController.previousBackStackEntry != null) {
                                // Pop the backstack if there is a previous route
                                navController.popBackStack()
                                onNavigationItemSelected.invoke(0)
                            } else {
                                // Handle double back press to exit the app
                                if (doubleBackToExitPressedOnce) {
                                    // Exit the app if back is pressed twice within 5 seconds
                                    activity?.finish()
                                } else {
                                    // Show the toast message and start a 5-second timer
                                    doubleBackToExitPressedOnce = true
                                    Toast.makeText(
                                        context,
                                        "برای خروج یک بار دیگر دکمه برگشت را بزنید",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // Reset the flag after 5 seconds using coroutine
                                    coroutineScope.launch {
                                        delay(5000)  // 5-second delay
                                        doubleBackToExitPressedOnce = false
                                    }
                                }
                            }
                        },
                    ) {
                        Icon(
                            modifier = Modifier.rotate(180f),
                            painter = painterResource(id = R.drawable.round_arrow_back_24),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
        )
    )
}