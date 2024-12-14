package com.nlhd.multimodulejetpackcompose.domain.usecases

import androidx.paging.PagingData
import com.nlhd.multimodulejetpackcompose.domain.repository.CharacterRepository
import com.nlhd.network.domain.models.character.Character
import kotlinx.coroutines.flow.Flow

class GetAllSeachByPage(
    private val repository: CharacterRepository
) {

    fun getAllSearchByPage(searchQuery: String): Flow<PagingData<Character>> = repository.getAllSearchByPage(searchQuery = searchQuery)

}