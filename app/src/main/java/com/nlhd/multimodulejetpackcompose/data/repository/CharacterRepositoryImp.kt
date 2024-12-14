package com.nlhd.multimodulejetpackcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nlhd.multimodulejetpackcompose.data.remote.CharacterPagingSource
import com.nlhd.multimodulejetpackcompose.data.remote.EpisodePagingSource
import com.nlhd.multimodulejetpackcompose.data.remote.SearchPagingSource
import com.nlhd.multimodulejetpackcompose.domain.repository.CharacterRepository
import com.nlhd.network.ApiOperation
import com.nlhd.network.KtorClient
import com.nlhd.network.domain.models.character.Character
import com.nlhd.network.domain.models.episode.Episode
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImp(
    private val ktorClient: KtorClient
): CharacterRepository {
    override suspend fun getCharacter(id: Int): ApiOperation<Character> {
        return ktorClient.getCharacter(id)
    }

    override fun getAllCharacterByPage() : Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(50),
            pagingSourceFactory = {
                CharacterPagingSource(ktorClient)
            }
        ).flow
    }

    override fun getAllEpisodeByPage(): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(50),
            pagingSourceFactory = {
                EpisodePagingSource(ktorClient)
            }
        ).flow
    }

    override fun getAllSearchByPage(searchQuery: String): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(50),
            pagingSourceFactory = {
                SearchPagingSource(ktorClient, searchQuery)
            }
        ).flow
    }

    override suspend fun getEpisodes(episodeId: List<Int>): ApiOperation<List<Episode>> {
        return ktorClient.getEpisodes(episodeId)
    }
}