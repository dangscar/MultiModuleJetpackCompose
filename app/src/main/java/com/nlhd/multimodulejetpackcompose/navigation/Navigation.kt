package com.nlhd.multimodulejetpackcompose.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nlhd.multimodulejetpackcompose.presentations.CharacterEpisode.CharacterEpisodeScreen
import com.nlhd.multimodulejetpackcompose.presentations.CharacterDetail.CharacterDetailsScreen
import com.nlhd.multimodulejetpackcompose.presentations.HomeScreen.HomeScreen
import com.nlhd.network.KtorClient
import javax.inject.Inject

sealed class NavDestination(val route: String, val title: String, val icon: ImageVector) {
    data object Home: NavDestination(route = "HomeScreen", title = "Home", icon = Icons.Default.Home)
    data object Episodes: NavDestination(route = "EpisodesScreen", title = "Episodes", icon = Icons.Default.DateRange)
    data object Search: NavDestination(route = "SearchScreen", title = "Search", icon = Icons.Default.Search)
}

@Composable
fun BottomBarItem(
    selected: Boolean,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick
        )
    ) {
        Icon(icon, contentDescription = "", tint = if (selected) Color.White else Color.Gray)
        Text(label, style = MaterialTheme.typography.labelSmall.copy(color = if (selected) Color.White else Color.Gray))
    }
}


@Composable
fun Navigation(ktorClient: KtorClient) {

    val navController = rememberNavController()
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val listDestination = listOf(NavDestination.Home, NavDestination.Episodes, NavDestination.Search)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFF222A35),
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.navigationBars).padding(5.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listDestination.forEachIndexed { index,item->
                    /*NavigationBarItem(
                        selected = selectedIndex == index,
                        icon = {
                            Icon(item.icon, contentDescription = "")
                        },
                        label = {
                            Text(item.title, style = MaterialTheme.typography.labelSmall)
                        },
                        onClick = {
                            selectedIndex = index
                            navController.navigate(route = item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.LightGray,
                            unselectedTextColor = Color.LightGray
                        )
                    )*/
                    BottomBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        icon = item.icon,
                        label = item.title,
                    ) {
                        selectedIndex = index
                        navController.navigate(route = item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }

                }
            }


        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = "HomeScreen") {
            composable(route = NavDestination.Home.route) {
                HomeScreen(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = 16.dp), ktorClient = ktorClient)
            }
            composable(route = NavDestination.Episodes.route) {
                Text("Episode")
            }
            composable(route = NavDestination.Search.route) {
                Text("Search")
            }

        }

    }
}