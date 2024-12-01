package com.nlhd.network.remote.episode

import com.nlhd.network.domain.models.episode.Episode
import com.nlhd.network.domain.models.episode.EpisodePage
import kotlinx.serialization.Serializable

@Serializable
data class RemoteEpisodePage(
    val info: Info,
    val results: List<RemoteEpisode>
) {
    @Serializable
    data class Info(
        val count: Int,
        val next: String?,
        val pages: Int,
        val prev: String?
    )
}

fun RemoteEpisodePage.toDomainEpisodePage(): EpisodePage {
    return EpisodePage(
        info = EpisodePage.Info(
            count = info.count,
            next = info.next,
            pages = info.pages,
            prev = info.prev
        ),
        results = results.map { it.toDomainEpisode() }
    )
}