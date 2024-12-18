package com.nlhd.multimodulejetpackcompose

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowInsetsControllerCompat
import com.nlhd.multimodulejetpackcompose.navigation.Navigation
import com.nlhd.multimodulejetpackcompose.ui.theme.MultiModuleJetpackComposeTheme
import com.nlhd.multimodulejetpackcompose.ui.theme.ResponsiveTheme
import com.nlhd.multimodulejetpackcompose.ui.theme.blackUi
import com.nlhd.network.KtorClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var ktorClient: KtorClient

    @SuppressLint("InvalidColorHexValue")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MultiModuleJetpackComposeTheme {
                val window = (LocalContext.current as Activity).window
                val controller = WindowInsetsControllerCompat(window, window.decorView)
                SideEffect {
                    window.statusBarColor = blackUi.toArgb()
                    controller.isAppearanceLightStatusBars = false
                    window.navigationBarColor = Color.Black.toArgb()
                    controller.isAppearanceLightNavigationBars = false
                }

                ResponsiveTheme {
                    Navigation(ktorClient)
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