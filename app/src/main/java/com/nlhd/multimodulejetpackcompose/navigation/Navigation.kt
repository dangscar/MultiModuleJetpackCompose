package com.nlhd.multimodulejetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nlhd.multimodulejetpackcompose.presentations.CharacterEpisode.CharacterEpisodeScreen
import com.nlhd.multimodulejetpackcompose.presentations.CharacterDetail.CharacterDetailsScreen
import com.nlhd.network.KtorClient
import javax.inject.Inject



@Composable
fun Navigation(ktorClient: KtorClient) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "character_details") {
        composable("character_details") {
            CharacterDetailsScreen(characterId = 1, ktorClient = ktorClient) {
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
    }
}