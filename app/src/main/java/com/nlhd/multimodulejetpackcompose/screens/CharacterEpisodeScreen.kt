package com.nlhd.multimodulejetpackcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterEpisodeScreen(characterId: Int) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF222A35)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
            Text("Character Episode Screen: $characterId", style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
        }
    }

}