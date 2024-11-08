package com.arash.altafi.englishteachercompose

import android.app.Activity
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.arash.altafi.englishteachercompose.sharedViewModel.DataStoreViewModel
import com.arash.altafi.englishteachercompose.ui.navigation.BackPressHandler
import com.arash.altafi.englishteachercompose.ui.navigation.BottomBar
import com.arash.altafi.englishteachercompose.ui.navigation.Fab
import com.arash.altafi.englishteachercompose.ui.navigation.Navigation
import com.arash.altafi.englishteachercompose.ui.navigation.NavigationDrawer
import com.arash.altafi.englishteachercompose.ui.navigation.TopBar
import com.arash.altafi.englishteachercompose.ui.theme.EnglishTeacherComposeTheme
import com.arash.altafi.englishteachercompose.utils.Constant

@Composable
fun AppTheme() {
    val context = LocalContext.current

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val dataStoreViewModel: DataStoreViewModel = hiltViewModel()

    val darkTheme: Boolean = isSystemInDarkTheme()
    val theme by dataStoreViewModel.cachedTheme.observeAsState()

    LaunchedEffect(theme) {
        val isDark = if (darkTheme) "dark" else "light"
        if (theme == "") {
            dataStoreViewModel.setTheme(isDark)
        }
    }

    var isScrolled by remember { mutableStateOf(false) }
    val fabVisible by remember { mutableStateOf(true) }

    var navigationSelectedItem by remember { mutableIntStateOf(0) }
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route
    val isSplashScreen = currentDestination == Constant.Routes.SPLASH
    val isHomeScreen = currentDestination == Constant.Routes.LEARN

    EnglishTeacherComposeTheme(
        darkTheme = theme == "dark"
    ) {
        NavigationDrawer(
            currentDestination = currentDestination,
            coroutineScope = coroutineScope,
            drawerState = drawerState,
            navController = navController,
            selectedItemIndex = selectedItemIndex,
            setSelectedItemIndex = { index ->
                selectedItemIndex = index
            }
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    if (!isSplashScreen) {
                        TopBar(
                            navController = navController,
                            coroutineScope = coroutineScope,
                            drawerState = drawerState,
                            isHomeScreen = isHomeScreen,
                            dataStoreViewModel = dataStoreViewModel,
                            context = context,
                            theme = theme
                        ) { newItem ->
                            navigationSelectedItem = newItem
                        }
                    }
                },
                bottomBar = {
                    if (currentDestination in Constant.AllowRouteToShowBottomBar) {
                        AnimatedVisibility(
                            visible = !isScrolled,
                            enter = fadeIn() + expandHorizontally() + slideInHorizontally(),
                            exit = fadeOut() + shrinkHorizontally() + slideOutHorizontally()
                        ) {
                            BottomBar(
                                navController = navController,
                                navigationSelectedItem = navigationSelectedItem
                            ) { newItem ->
                                navigationSelectedItem = newItem
                            }
                        }
                    }
                },
                floatingActionButton = {
                    AnimatedVisibility(visible = fabVisible && isHomeScreen) {
                        Fab(context = context)
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
            ) { innerPadding ->
                Navigation(
                    navController = navController,
                    innerPadding = innerPadding
                )

                BackPressHandler(navController) { newItem ->
                    if (currentDestination in Constant.AllowRouteToShowBottomBar)
                        navigationSelectedItem = newItem
                }
            }
        }
    }
}