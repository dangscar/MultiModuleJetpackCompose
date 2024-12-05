package com.nlhd.multimodulejetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable(
            "character_details/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val characterId = navBackStackEntry.arguments?.getInt("characterId") ?: 0
            CharacterDetailsScreen(characterId = characterId) {
                navController.navigate("character_episodes/$it")
            }
        }
        composable(
            route = "character_episodes/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
            CharacterEpisodeScreen(characterId, ktorClient = ktorClient)
        }
        composable(route = "HomeScreen") {
            HomeScreen(navController = navController)
        }
    }
}