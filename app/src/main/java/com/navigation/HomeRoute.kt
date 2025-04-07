package com.navigation

import androidx.compose.runtime.Composable
import com.data.getUserData
import com.example.appnavigate.ui.screen.HomeScreen

@Composable
fun HomeRoute(userName: String) {
    val user = getUserData(userName = userName)

    HomeScreen(user = user)
}