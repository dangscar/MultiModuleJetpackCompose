package com.nlhd.multimodulejetpackcompose.domain.repository

import androidx.paging.PagingData
import com.nlhd.network.ApiOperation
import com.nlhd.network.domain.models.character.Character
import com.nlhd.network.domain.models.episode.Episode
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getCharacter(id: Int): ApiOperation<Character>

    suspend fun getEpisodes(episodeId: List<Int>): ApiOperation<List<Episode>>

    fun getAllCharacterByPage(): Flow<PagingData<Character>>

}