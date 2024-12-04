package com.nlhd.multimodulejetpackcompose.domain.repository

import com.nlhd.network.ApiOperation
import com.nlhd.network.domain.models.character.Character
import com.nlhd.network.domain.models.episode.Episode

interface CharacterRepository {

    suspend fun getCharacter(id: Int): ApiOperation<Character>

    suspend fun getEpisodes(episodeId: List<Int>): ApiOperation<List<Episode>>

}