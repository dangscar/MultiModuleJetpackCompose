package com.nlhd.multimodulejetpackcompose.presentations.SeachScreen.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.nlhd.network.domain.models.character.Character

@Composable
fun SearchList(
    characters: LazyPagingItems<Character>
) {
    LazyColumn {
        item {
            if (characters.loadState.refresh is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (characters.loadState.refresh is LoadState.Error) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                    Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        items(characters.itemCount) { pos->
            val character = characters[pos]
            character?.let {
                SearchItem(character)
            }
        }
        item {
            if (characters.loadState.append is LoadState.Loading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun SearchItem(
    character: Character
) {
    Spacer(modifier = Modifier.height(5.dp))
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Column(
            modifier = Modifier.height(150.dp).padding(5.dp)
        ) {
            Text(
                "Name",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White
                ),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                character.name,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White
                ),
                modifier = Modifier,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Last known location",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White
                ),
                modifier = Modifier,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                character.location.name,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White
                ),
                modifier = Modifier,
                maxLines = 1
            )
        }

    }

}

@Preview
@Composable
private fun SearchLists() {
    //SearchList()
}