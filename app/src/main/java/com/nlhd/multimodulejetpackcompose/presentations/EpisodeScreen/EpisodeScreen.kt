package com.nlhd.multimodulejetpackcompose.presentations.EpisodeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.nlhd.multimodulejetpackcompose.connectivityState
import com.nlhd.multimodulejetpackcompose.presentations.commonConponent.TopBar
import com.nlhd.multimodulejetpackcompose.ui.theme.ResponsiveTheme
import com.nlhd.multimodulejetpackcompose.ui.theme.blackUi
import com.nlhd.multimodulejetpackcompose.ui.theme.blueUi
import com.nlhd.multimodulejetpackcompose.ui.theme.paddingSystemMedium
import com.nlhd.multimodulejetpackcompose.ui.theme.paddingSystemSmall
import com.nlhd.network.domain.models.episode.Episode

@Composable
fun EpisodeScreen(
    modifier: Modifier = Modifier,
    viewModel: EpisodeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val episodes = state.value?.collectAsLazyPagingItems()
    val isConnected by connectivityState()
    LaunchedEffect(key1 = isConnected) {
        if (isConnected) {
            episodes?.retry()
        }
    }

    Column(
        modifier = modifier
    ) {
        TopBar("All Episode")
        if (episodes != null) {
            EpisodeList(episodes)
        }


    }

}

@Composable
fun StickyHeader(seasonNumber: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.White, shape = RoundedCornerShape(ResponsiveTheme.appDimensRes.border))
        .background(color = blackUi),
    ) {
        Text(
            "Season $seasonNumber",
            style = ResponsiveTheme.appTypographyRes.titleLarge.copy(
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(ResponsiveTheme.appDimensRes.small3),
            maxLines = 1
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EpisodeList(episodes: LazyPagingItems<Episode>) {
    if (episodes.loadState.refresh is LoadState.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else if (episodes.loadState.refresh is LoadState.Error) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            episodes.itemSnapshotList.items.groupBy { it.seasonNumber }.forEach { (seasonNumber, episodes) ->
                stickyHeader {
                    StickyHeader(seasonNumber = seasonNumber.toString())
                }
                items(episodes.size) { pos->
                    val episode = episodes[pos]
                    EpisodeItem(episode)
                }
            }
        }
    }

}

@Composable
fun EpisodeItem(episode: Episode) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            Text(
                "Episode",
                style = ResponsiveTheme.appTypographyRes.titleMedium.copy(
                    color = blueUi
                ),
                maxLines = 1
            )
            Text(
                episode.episodeNumber.toString(),
                style = ResponsiveTheme.appTypographyRes.bodyMedium.copy(
                    color = Color.White
                ),
                maxLines = 1
            )
        }
        Column(
            modifier = Modifier.weight(0.5f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                episode.name,
                style = ResponsiveTheme.appTypographyRes.titleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                ),
                maxLines = 1
            )
            Text(
                episode.airDate,
                style = ResponsiveTheme.appTypographyRes.bodyMedium.copy(
                    color = Color.White,
                    textAlign = TextAlign.End
                ),
                maxLines = 1
            )
        }

    }
}

@Preview
@Composable
private fun EpisodeScreens() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(blackUi)
    ) {
        //EpisodeScreen()
    }

}