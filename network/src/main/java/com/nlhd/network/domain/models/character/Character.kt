package com.nlhd.network.domain.models.character

data class Character(
    val created: String,
    val episode: List<Int>,
    val gender: CharacterGender,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: CharacterStatus,
    val type: String,
    val url: String
) {
    data class Location(
        val name: String,
        val url: String
    )
    data class Origin(
        val name: String,
        val url: String
    )
}