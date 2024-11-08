package com.arash.altafi.englishteachercompose.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun BottomBar(
    navController: NavHostController,
    navigationSelectedItem: Int,
    onNavigationItemSelected: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp)),
        containerColor = Color.Red
    ) {
        bottomNavigationItems().forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = index == navigationSelectedItem,
                label = {
                    Text(navigationItem.label)
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (navigationItem.badgeCount != 0) {
                                Badge {
                                    Text(text = navigationItem.badgeCount.toString())
                                }
                            } else {
                                Badge()
                            }
                        },
                    ) {
                        Icon(
                            painter = painterResource(navigationItem.icon),
                            contentDescription = navigationItem.label
                        )
                    }
                },
                onClick = {
                    onNavigationItemSelected.invoke(index)
                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Magenta,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.Green,
                    unselectedTextColor = Color.Yellow,
                    indicatorColor = Color.Cyan
                )
            )
        }
    }
}