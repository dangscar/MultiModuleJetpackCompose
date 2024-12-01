package com.nlhd.multimodulejetpackcompose

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nlhd.multimodulejetpackcompose.screens.CharacterDetailsScreen
import com.nlhd.multimodulejetpackcompose.screens.CharacterEpisodeScreen
import com.nlhd.multimodulejetpackcompose.ui.theme.MultiModuleJetpackComposeTheme
import com.nlhd.network.KtorClient
import com.nlhd.network.domain.models.character.Character

class MainActivity : ComponentActivity() {

    private val ktorClient = KtorClient()

    @SuppressLint("InvalidColorHexValue")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val window = (LocalContext.current as Activity).window
            val controller = WindowInsetsControllerCompat(window, window.decorView)
            SideEffect {
                window.statusBarColor = Color(0xFF222A3).toArgb()
                controller.isAppearanceLightStatusBars = false
                window.navigationBarColor = Color.Black.toArgb()
                controller.isAppearanceLightNavigationBars = false
            }
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
                    CharacterEpisodeScreen(characterId)
                }
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiModuleJetpackComposeTheme {

    }
}