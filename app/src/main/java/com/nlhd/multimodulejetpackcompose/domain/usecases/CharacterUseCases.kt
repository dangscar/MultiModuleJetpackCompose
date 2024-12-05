package com.nlhd.multimodulejetpackcompose.domain.usecases

data class CharacterUseCases(
    val getCharacter: GetCharacter,
    val getEpisodes: GetEpisodes,
    val getAllCharacterByPage: GetAllCharacterByPage
)