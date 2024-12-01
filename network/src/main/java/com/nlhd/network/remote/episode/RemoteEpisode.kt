package com.nlhd.network.remote.episode

import com.nlhd.network.domain.models.episode.Episode
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEpisode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)

fun RemoteEpisode.toDomainEpisode() : Episode {
    return Episode(
        airDate = air_date,
        charactersIdsInEpisode = characters.map { it.substring(it.lastIndexOf("/") + 1).toInt() },
        id = id,
        seasonNumber = episode.filter { it.isDigit() }.take(2).toInt(),
        name = name,
        episodeNumber = episode.filter { it.isDigit() }.takeLast(2).toInt()
    )
}