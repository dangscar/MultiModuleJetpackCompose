package com.nlhd.network.remote.character

import com.nlhd.network.domain.models.character.CharacterPage
import kotlinx.serialization.Serializable

@Serializable
data class RemoteCharacterPage(
    val info: Info,
    val results: List<RemoteCharacter>
) {
    @Serializable
    data class Info(
        val count: Int,
        val next: String?,
        val pages: Int,
        val prev: String?
    )
}

fun RemoteCharacterPage.toDomainCharacterPage(): CharacterPage {
    return CharacterPage(
        info = CharacterPage.Info(
            count = info.count,
            next = info.next,
            pages = info.pages,
            prev = info.prev
        ),
        results = results.map { it.toDomainCharacter() }
    )
}
