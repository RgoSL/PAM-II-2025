package com.navigation

import com.navigation.Routes.HOME_ROUTE
import com.navigation.Routes.LOGIN_ROUTE

sealed class Screens(val route: String) {
    object LoginScreen : Screens (LOGIN_ROUTE)
    object HomeScreen : Screens (HOME_ROUTE)
}