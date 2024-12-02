package com.nlhd.multimodulejetpackcompose.presentations.CharacterEpisode

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
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
import coil.compose.AsyncImage
import com.nlhd.multimodulejetpackcompose.components.CharacterDetailNameComponent
import com.nlhd.multimodulejetpackcompose.components.EpisodeComponent
import com.nlhd.network.KtorClient
import com.nlhd.network.domain.models.character.Character
import com.nlhd.network.domain.models.episode.Episode
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterEpisodeScreen(characterId: Int, ktorClient: KtorClient) {
    var character by remember {
        mutableStateOf<Character?>(null)
    }
    var episodes by remember {
        mutableStateOf<List<Episode>>(emptyList())
    }


    LaunchedEffect(key1 = Unit) {
        ktorClient.getCharacter(characterId).onSuccess {
            character = it
            launch {
                ktorClient.getEpisodes(character!!.episode).onSuccess { episodesRes ->
                    episodes = episodesRes
                    episodes.forEach { episode ->
                        Log.d("AAA", episode.seasonNumber.toString())
                    }
                }.onFailure {  }
            }
        }.onFailure {

        }


    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF222A35)
    ) {
        when (character) {
            null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(it).padding(16.dp)
                ) {
                    item {
                        CharacterDetailNameComponent(character!!.status, character!!.name)
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    item {
                        AsyncImage(
                            model = character!!.image,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth().aspectRatio(1f)
                                .clip(RoundedCornerShape(12.dp)),

                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    episodes.groupBy {
                        it.seasonNumber
                    }.forEach { mapEntry ->
                        stickyHeader {
                            SeasonHeader(mapEntry.key)
                        }
                        item {
                            mapEntry.value.forEach { episode ->
                                Spacer(modifier = Modifier.height(10.dp))
                                EpisodeComponent(episode)

                            }
                        }
                    }
                }
            }
        }


    }
}