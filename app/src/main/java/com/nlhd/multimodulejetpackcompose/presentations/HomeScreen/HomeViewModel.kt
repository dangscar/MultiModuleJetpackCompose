package com.nlhd.multimodulejetpackcompose.presentations.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nlhd.multimodulejetpackcompose.domain.usecases.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
): ViewModel() {

    val getAllCharacter = characterUseCases.getAllCharacterByPage().cachedIn(viewModelScope)

}