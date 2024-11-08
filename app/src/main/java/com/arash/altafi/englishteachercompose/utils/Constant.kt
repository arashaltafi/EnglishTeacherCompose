package com.arash.altafi.englishteachercompose.utils

object Constant {
    object CategoryRoutes {
        const val APP_ROUTES = "AppRoutes"
        const val AUTH_ROUTES = "AuthRoutes"
    }

    object Routes {
        const val SPLASH = "Splash"
        const val LEARN = "Learn"
        const val DICTIONARY = "Dictionary"
        const val SETTING = "Setting"
        const val PRONUNCIATION = "Pronunciation"

        const val LOGIN = "Login"
        const val REGISTER = "Register"
    }

    object EndPoints {
        const val LOGIN = "login"
    }

    val AllowRouteToShowBottomBar = arrayOf(
        Routes.LEARN,
        Routes.SETTING,
        Routes.DICTIONARY,
        Routes.PRONUNCIATION,
    )

}