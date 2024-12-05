package com.nlhd.multimodulejetpackcompose.presentations.CharacterDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.nlhd.multimodulejetpackcompose.presentations.components.CharacterCategory
import com.nlhd.multimodulejetpackcompose.presentations.components.CharacterDetailNameComponent
import com.nlhd.network.KtorClient
import com.nlhd.network.domain.models.character.Character

@Composable
fun CharacterDetailsScreen(
    characterId: Int,
    viewModel: CharacterDetailsViewModel = hiltViewModel(),
    onClickViewEpisode: (Int) -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getCharacter(characterId)
    }

    val state by viewModel.internalStorageFlow.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF222A35)
    ) {

        when (state) {
            is CharacterDetailsViewState.Error -> {

            }
            CharacterDetailsViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is CharacterDetailsViewState.Success -> {
                val character = (state as CharacterDetailsViewState.Success).data
                LazyColumn(modifier = Modifier.padding(it).padding(16.dp)) {
                    item {
                        Column {
                            CharacterDetailNameComponent(characterStatus = character.status, displayName = character.name)
                            Spacer(Modifier.height(10.dp))
                            AsyncImage(
                                model = character.image,
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth().aspectRatio(1f)
                                    .clip(RoundedCornerShape(12.dp)),

                                )
                            Spacer(Modifier.height(10.dp))
                            CharacterCategory("Last known location", character.location.name)
                            Spacer(Modifier.height(10.dp))
                            CharacterCategory("Species", character.species)
                            Spacer(Modifier.height(10.dp))
                            CharacterCategory("Gender", character.gender.displayName)
                            Spacer(Modifier.height(10.dp))
                            CharacterCategory("Origin", character.origin.name)
                            Spacer(Modifier.height(10.dp))
                            CharacterCategory("Episodes", character.episode.size.toString())
                            Spacer(Modifier.height(10.dp))
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                Button(onClick = { onClickViewEpisode(character.id)},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(40.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                                    border = BorderStroke(2.dp, color = Color.White)
                                ) {
                                    Text("View all episodes", style = MaterialTheme.typography.titleMedium.copy(color = Color.White))
                                }
                            }
                        }
                    }
                }
            }
        }




    }



}