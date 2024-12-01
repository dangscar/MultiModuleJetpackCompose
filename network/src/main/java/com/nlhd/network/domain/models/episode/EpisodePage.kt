package com.nlhd.network.domain.models.episode

data class EpisodePage(
    val info: Info,
    val results: List<Episode>
) {
    data class Info(
        val count: Int,
        val next: String?,
        val pages: Int,
        val prev: String?
    )
}

