package com.arash.altafi.englishteachercompose.ui.navigation

import com.arash.altafi.englishteachercompose.R
import com.arash.altafi.englishteachercompose.utils.Constant

data class NavigationDrawerItem(
    val label: String,
    val icon: Int,
    val badgeCount: Int,
    val route: String,
)

data class BottomNavigationItem(
    val label: String,
    val icon: Int,
    val badgeCount: Int,
    val route: String,
)

fun navigationDrawerItems(): List<NavigationDrawerItem> {
    return listOf(
        NavigationDrawerItem(
            label = "آموزش",
            icon = R.drawable.ic_launcher_foreground,
            badgeCount = 2,
            route = Constant.Routes.LEARN,
        ),
        NavigationDrawerItem(
            label = "تلفظ",
            icon = R.drawable.ic_launcher_foreground,
            badgeCount = 0,
            route = Constant.Routes.PRONUNCIATION,
        ),
        NavigationDrawerItem(
            label = "دیکشنری",
            icon = R.drawable.ic_launcher_foreground,
            badgeCount = 0,
            route = Constant.Routes.DICTIONARY,
        ),
        NavigationDrawerItem(
            label = "تنظیمات",
            icon = R.drawable.ic_launcher_foreground,
            badgeCount = 0,
            route = Constant.Routes.SETTING,
        ),
    )
}

fun bottomNavigationItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            label = "آموزش",
            icon = R.drawable.ic_launcher_foreground,
            badgeCount = 2,
            route = Constant.Routes.LEARN,
        ),
        BottomNavigationItem(
            label = "تلفظ",
            icon = R.drawable.ic_launcher_foreground,
            badgeCount = 0,
            route = Constant.Routes.PRONUNCIATION,
        ),
        BottomNavigationItem(
            label = "دیکشنری",
            icon = R.drawable.ic_launcher_foreground,
            badgeCount = 0,
            route = Constant.Routes.DICTIONARY,
        ),
        BottomNavigationItem(
            label = "تنظیمات",
            icon = R.drawable.ic_launcher_foreground,
            badgeCount = 0,
            route = Constant.Routes.SETTING,
        ),
    )
}