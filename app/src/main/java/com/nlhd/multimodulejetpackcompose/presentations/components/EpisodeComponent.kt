package com.nlhd.multimodulejetpackcompose.presentations.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nlhd.network.domain.models.episode.Episode

@Composable
fun EpisodeComponent(episode: Episode) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            Text(
                text = "Episode",
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFFB2EFF8)
            )
            Text(
                text = episode.episodeNumber.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            Text(
                text = episode.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = episode.airDate,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun EpisodeComponents() {
    EpisodeComponent(episode = Episode(
        id = 1,
        name = "Close Rick-counters of the Rick Kind",
        airDate = "December 2, 2013",
        episodeNumber = 1,
        seasonNumber = 1,
        charactersIdsInEpisode = emptyList()
    )
    )
}