package com.nlhd.multimodulejetpackcompose.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nlhd.multimodulejetpackcompose.presentations.CharacterEpisode.CharacterEpisodeScreen
import com.nlhd.multimodulejetpackcompose.presentations.CharacterDetail.CharacterDetailsScreen
import com.nlhd.multimodulejetpackcompose.presentations.HomeScreen.HomeScreen
import com.nlhd.network.KtorClient
import javax.inject.Inject



@Composable
fun Navigation(ktorClient: KtorClient) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Main") {
        navigation(route = "Main", startDestination = "MainScreen") {
            composable(route = "MainScreen") {
                //HomeScreen(navController = navController)
                MainScreenNavigation(ktorClient)
            }


        }


    }
}