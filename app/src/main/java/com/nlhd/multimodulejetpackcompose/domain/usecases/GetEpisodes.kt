package com.nlhd.multimodulejetpackcompose.domain.usecases

import com.nlhd.multimodulejetpackcompose.domain.repository.CharacterRepository
import com.nlhd.network.ApiOperation
import com.nlhd.network.domain.models.episode.Episode

class GetEpisodes(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(episodeId: List<Int>): ApiOperation<List<Episode>> = repository.getEpisodes(episodeId)
}