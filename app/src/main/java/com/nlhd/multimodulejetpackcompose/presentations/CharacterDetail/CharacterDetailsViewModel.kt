package com.nlhd.multimodulejetpackcompose.presentations.CharacterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nlhd.multimodulejetpackcompose.domain.usecases.CharacterUseCases
import com.nlhd.network.domain.models.character.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
): ViewModel()  {

    private var _internalStorageFlow: MutableStateFlow<CharacterDetailsViewState> = MutableStateFlow(CharacterDetailsViewState.Loading)
    val internalStorageFlow: StateFlow<CharacterDetailsViewState> = _internalStorageFlow.asStateFlow()

    fun getCharacter(characterId: Int) = viewModelScope.launch {
        characterUseCases.getCharacter(characterId).onSuccess { character->
            _internalStorageFlow.update {
                return@update CharacterDetailsViewState.Success(data = character)
            }


        }.onFailure { exception ->
            _internalStorageFlow.update {
                return@update CharacterDetailsViewState.Error(message = exception.message.toString())
            }
        }
    }

}

sealed interface CharacterDetailsViewState {
    data object Loading: CharacterDetailsViewState
    data class Error(val message: String): CharacterDetailsViewState
    data class Success(val data: Character): CharacterDetailsViewState
}