package com.nlhd.multimodulejetpackcompose.presentations.SeachScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nlhd.multimodulejetpackcompose.domain.usecases.CharacterUseCases
import com.nlhd.network.domain.models.character.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
): ViewModel() {

    private var _state: MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(event.query)
            }
            is SearchEvent.Search -> {
                _state.value = state.value.copy(characters = getAllSearchByPage(state.value.queryString))
            }
        }
    }

    fun getAllSearchByPage(searchQuery: String): Flow<PagingData<Character>> = characterUseCases.getAllSearchByPage.getAllSearchByPage(searchQuery = searchQuery).cachedIn(viewModelScope)
}

