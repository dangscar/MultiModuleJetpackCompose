package com.nlhd.multimodulejetpackcompose.presentations.SeachScreen

import androidx.paging.PagingData
import com.nlhd.network.domain.models.character.Character
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val queryString: String = "",
    val characters: Flow<PagingData<Character>>? = null

) {
}