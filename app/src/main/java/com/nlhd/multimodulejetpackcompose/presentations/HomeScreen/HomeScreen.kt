package com.nlhd.multimodulejetpackcompose.presentations.HomeScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nlhd.multimodulejetpackcompose.presentations.CharacterGrid.CharacterItem

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val getAllCharacter = viewModel.getAllCharacter.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFF222A35)
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            if (getAllCharacter.loadState.refresh is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else if (getAllCharacter.loadState.refresh is LoadState.Error) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                    Log.d("AAA", "error")
                }
            }
            else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
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

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreens() {

}