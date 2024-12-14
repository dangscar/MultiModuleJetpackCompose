package com.nlhd.multimodulejetpackcompose.presentations.CharacterGrid

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nlhd.multimodulejetpackcompose.ui.theme.ResponsiveTheme
import com.nlhd.multimodulejetpackcompose.ui.theme.blueUi
import com.nlhd.network.domain.models.character.Character


@Composable
fun CharacterItem(
    character: Character,
    onClickCharacter: (Character) -> Unit
) {
    Card(
        modifier = Modifier.clip(RoundedCornerShape(12.dp)).clickable { onClickCharacter(character) }.padding(ResponsiveTheme.appDimensRes.small1),
        shape = ShapeDefaults.Large,
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.verticalGradient(listOf(Color.Transparent, blueUi))
        ),
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = character.image,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                CircleStatus()
            }


            Text(
                character.name,
                style = ResponsiveTheme.appTypographyRes.titleMedium.copy(
                    color = blueUi,
                    textAlign = TextAlign.Center
                ),
                maxLines = 1
            )
        }
    }

}

@Composable
fun CircleStatus() {
    Box(
        modifier = Modifier.background(
            shape = CircleShape,
            brush = Brush.verticalGradient(listOf(Color.Black, Color.Transparent))
        )
    ) {
        Box(modifier = Modifier.padding(4.dp)
            .background(color = Color.Green, shape = CircleShape)
            .size(10.dp)
            .clip(CircleShape)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CharacterListItems() {
    //CharacterListItem()
}