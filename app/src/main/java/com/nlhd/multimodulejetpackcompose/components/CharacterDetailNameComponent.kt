package com.nlhd.multimodulejetpackcompose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nlhd.network.domain.models.character.CharacterStatus

@Composable
fun CharacterDetailNameComponent(
    characterStatus: CharacterStatus,
    displayName: String
) {
    Column {
        CharacterStatusComponent(characterStatus)
        Spacer(modifier = Modifier.height(10.dp))
        Text(displayName, style = MaterialTheme.typography.titleLarge, color = Color(0xFFB2EFF8))
    }
}

@Preview(showBackground = true)
@Composable
private fun CharacterDetailNameComponentTest() {

}