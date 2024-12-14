package com.nlhd.multimodulejetpackcompose.presentations.EpisodeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nlhd.multimodulejetpackcompose.domain.usecases.CharacterUseCases
import com.nlhd.network.domain.models.episode.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
): ViewModel() {

    private var _state: MutableStateFlow<Flow<PagingData<Episode>>?> = MutableStateFlow(null)
    val state: StateFlow<Flow<PagingData<Episode>>?> = _state.asStateFlow()

    init {
        _state.value = getAllEpisodeByPage()
    }
    fun getAllEpisodeByPage() = characterUseCases.getAllEpisodeByPage().cachedIn(viewModelScope)
}