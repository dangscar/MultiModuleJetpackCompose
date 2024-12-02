package com.nlhd.multimodulejetpackcompose.presentations.CharacterEpisode

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun SeasonHeader(seasonNumber: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .border(2.dp, Color.White, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .padding(12.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Season $seasonNumber",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
        )
    }
}

@Preview()
@Composable
private fun SeasonHeaders() {
    //SeasonHeader()
}