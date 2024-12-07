package com.nlhd.multimodulejetpackcompose.presentations.EpisodeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nlhd.multimodulejetpackcompose.domain.usecases.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
): ViewModel() {

    fun getAllEpisodeByPage() = characterUseCases.getAllEpisodeByPage().cachedIn(viewModelScope)
}