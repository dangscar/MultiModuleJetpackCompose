package com.nlhd.network.domain.models.character

import androidx.compose.ui.graphics.Color

sealed class CharacterStatus(val displayName: String, val color: Color) {

    data object Alive: CharacterStatus("Alive", color = Color.Green)
    data object Dead: CharacterStatus("Dead", color = Color.Red)
    data object Unknown: CharacterStatus("Unknown",  color = Color.Yellow)

}