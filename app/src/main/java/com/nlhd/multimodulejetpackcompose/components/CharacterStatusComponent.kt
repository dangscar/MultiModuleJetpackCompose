package com.nlhd.multimodulejetpackcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nlhd.network.domain.models.character.CharacterStatus

@Composable
fun CharacterStatusComponent(characterStatus: CharacterStatus) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, color = characterStatus.color, shape = RoundedCornerShape(12.dp))
            .background(color = Color.Transparent)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "Status: ", style = MaterialTheme.typography.titleMedium.copy(color = Color.White))
        Text(text = characterStatus.displayName, style = MaterialTheme.typography.titleMedium.copy(color = Color.White))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CharacterStatusComponents() {
    CharacterStatusComponent(CharacterStatus.Unknown)
}