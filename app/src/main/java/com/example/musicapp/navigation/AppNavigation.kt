package com.example.musicapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.screens.main.main.MainScreen
import com.example.musicapp.screens.main.main.MainScreenViewModel
import com.example.musicapp.screens.main.player.PlayerScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val mainViewModel = hiltViewModel<MainScreenViewModel>()

    NavHost(navController = navController, startDestination = AppRoutes.MainScreen.name){
        composable(route = AppRoutes.MainScreen.name) {
            MainScreen(navController, mainViewModel) }
        composable(route = AppRoutes.PlayerScreen.name) {
                PlayerScreen(navController, mainViewModel)
        }
    }
}