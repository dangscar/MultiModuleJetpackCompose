package com.nlhd.multimodulejetpackcompose.data.remote

import com.nlhd.multimodulejetpackcompose.domain.repository.CharacterRepository
import com.nlhd.network.ApiOperation
import com.nlhd.network.KtorClient
import com.nlhd.network.domain.models.character.Character
import com.nlhd.network.domain.models.episode.Episode

class CharacterRepositoryImp(
    private val ktorClient: KtorClient
): CharacterRepository {
    override suspend fun getCharacter(id: Int): ApiOperation<Character> {
        return ktorClient.getCharacter(id)
    }

    override suspend fun getEpisodes(episodeId: List<Int>): ApiOperation<List<Episode>> {
        return ktorClient.getEpisodes(episodeId)
    }
}