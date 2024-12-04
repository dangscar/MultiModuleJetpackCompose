package com.nlhd.multimodulejetpackcompose.domain.usecases

import com.nlhd.multimodulejetpackcompose.domain.repository.CharacterRepository
import com.nlhd.network.ApiOperation
import com.nlhd.network.domain.models.character.Character

class GetCharacter(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): ApiOperation<Character> = repository.getCharacter(id)
}