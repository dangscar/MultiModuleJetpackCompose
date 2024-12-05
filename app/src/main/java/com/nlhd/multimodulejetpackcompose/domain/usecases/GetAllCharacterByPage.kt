package com.nlhd.multimodulejetpackcompose.domain.usecases

import androidx.paging.PagingData
import com.nlhd.multimodulejetpackcompose.domain.repository.CharacterRepository
import com.nlhd.network.domain.models.character.Character
import kotlinx.coroutines.flow.Flow

class GetAllCharacterByPage(
    private val repository: CharacterRepository

) {

    operator fun invoke(): Flow<PagingData<Character>> = repository.getAllCharacterByPage()

}