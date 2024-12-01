package com.nlhd.network.domain.models.character

data class CharacterPage(
    val info: Info,
    val results: List<Character>
) {
    data class Info(
        val count: Int,
        val next: String?,
        val pages: Int,
        val prev: String?
    )
}