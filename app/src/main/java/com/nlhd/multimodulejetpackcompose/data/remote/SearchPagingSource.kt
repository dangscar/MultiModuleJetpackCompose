package com.nlhd.multimodulejetpackcompose.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nlhd.network.KtorClient
import com.nlhd.network.domain.models.character.Character

class SearchPagingSource(
    private val ktorClient: KtorClient,
    val searchQuery: String
): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
            val characterPage = ktorClient.getAllSearchByPage(1, searchQuery = searchQuery)
            val endOfPaginationReached = characterPage.info.pages == page
            if (!endOfPaginationReached) {
                LoadResult.Page(
                    data = characterPage.results,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (endOfPaginationReached) null else page + 1)
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