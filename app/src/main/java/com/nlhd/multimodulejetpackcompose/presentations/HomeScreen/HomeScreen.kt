package com.nlhd.multimodulejetpackcompose.presentations.HomeScreen

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nlhd.multimodulejetpackcompose.connectivityState
import com.nlhd.multimodulejetpackcompose.presentations.CharacterDetailScreen.CharacterDetailsScreen
import com.nlhd.multimodulejetpackcompose.presentations.CharacterEpisode.CharacterEpisodeScreen
import com.nlhd.multimodulejetpackcompose.presentations.CharacterGrid.CharacterItem
import com.nlhd.network.KtorClient

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    ktorClient: KtorClient
) {

    val navController = rememberNavController()
    val isConnected by connectivityState()
    val getAllCharacter = viewModel.getAllCharacter.collectAsLazyPagingItems()
    LaunchedEffect(key1 = isConnected) {
        if (isConnected) {
            getAllCharacter.retry()
        }
    }

    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            if (getAllCharacter.loadState.refresh is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else if (getAllCharacter.loadState.refresh is LoadState.Error) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                    Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier
                ) {
                    items(getAllCharacter.itemCount) { pos->
                        val character = getAllCharacter[pos]
                        character?.let {
                            CharacterItem(character) {
                                navController.navigate("character_details/${character.id}")
                            }
                        }

                    }
                    item {
                        if (getAllCharacter.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
        composable(
            route="character_details/{characterId}",
            arguments = listOf(
                navArgument("characterId") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val characterId = navBackStackEntry.arguments?.getInt("characterId") ?: 0
            CharacterDetailsScreen(characterId = characterId, modifier = modifier) {
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
            CharacterEpisodeScreen(characterId, ktorClient = ktorClient, modifier = modifier)

        }
    }


}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreens() {
    NavigationBar(
        containerColor = Color.Transparent
    ) {

    }

}