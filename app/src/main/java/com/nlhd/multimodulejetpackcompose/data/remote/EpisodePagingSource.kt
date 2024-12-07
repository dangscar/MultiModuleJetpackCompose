package com.nlhd.multimodulejetpackcompose.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nlhd.network.KtorClient
import com.nlhd.network.domain.models.episode.Episode

class EpisodePagingSource(
    private val ktorClient: KtorClient
): PagingSource<Int, Episode>(){
    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val page = params.key ?: 1
        return try {
            val episodePage = ktorClient.getAllEpisodeByPage(1)
            val endOfPaginationReached = episodePage.info.pages == page
            if (!endOfPaginationReached) {
                LoadResult.Page(
                    data = episodePage.results,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (endOfPaginationReached) null else page + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}