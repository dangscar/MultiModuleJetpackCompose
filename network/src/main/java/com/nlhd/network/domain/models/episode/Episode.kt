package com.nlhd.network.domain.models.episode

data class Episode(
    val airDate: String,
    val charactersIdsInEpisode: List<Int>,
    val id: Int,
    val seasonNumber: Int,
    val name: String,
    val episodeNumber: Int,
)


