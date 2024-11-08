package com.arash.altafi.englishteachercompose.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import com.arash.altafi.englishteachercompose.R
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationDrawer(
    currentDestination: String? = null,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavHostController,
    selectedItemIndex: Int,
    setSelectedItemIndex: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        gesturesEnabled = currentDestination != "mapbox",
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = colorResource(R.color.gray_200),
                drawerContentColor = colorResource(R.color.white),
                drawerShape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
            ) {
                Spacer(Modifier.height(16.dp))
                navigationDrawerItems().forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        modifier = Modifier
                            .padding(NavigationDrawerItemDefaults.ItemPadding),
                        label = { Text(item.label) },
                        selected = index == selectedItemIndex,
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label
                            )
                        },
                        badge = {
                            if (item.badgeCount > 0) {
                                Badge {
                                    Text(item.badgeCount.toString())
                                }
                            } else {
                                Badge()
                            }
                        },
                        onClick = {
                            navController.navigate(item.label)
                            setSelectedItemIndex.invoke(index)
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        },
        drawerState = drawerState,
    ) {
        content.invoke()
    }
}