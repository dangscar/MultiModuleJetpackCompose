package com.nlhd.multimodulejetpackcompose.presentations.SeachScreen

sealed class SearchEvent {
    data class UpdateSearchQuery(val query: String): SearchEvent()
    data object Search: SearchEvent()
}