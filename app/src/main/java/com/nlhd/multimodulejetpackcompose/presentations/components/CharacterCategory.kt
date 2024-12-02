package com.nlhd.multimodulejetpackcompose.presentations.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CharacterCategory(
    title: String,
    name: String
) {
    Column {
        Text(text = title, style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFFB2EFF8)))
        Text(text = name, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp, color = Color.White))
    }
}

@Preview(showBackground = true)
@Composable
private fun Cateroty() {
    CharacterCategory("Location", "AA")
}