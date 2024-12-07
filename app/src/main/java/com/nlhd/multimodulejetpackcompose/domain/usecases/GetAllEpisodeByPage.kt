package com.nlhd.multimodulejetpackcompose.domain.usecases

import androidx.paging.PagingData
import com.nlhd.multimodulejetpackcompose.domain.repository.CharacterRepository
import com.nlhd.network.domain.models.episode.Episode
import kotlinx.coroutines.flow.Flow

class GetAllEpisodeByPage(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<PagingData<Episode>> = repository.getAllEpisodeByPage()
}