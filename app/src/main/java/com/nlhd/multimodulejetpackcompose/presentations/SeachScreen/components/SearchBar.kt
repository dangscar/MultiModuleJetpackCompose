package com.nlhd.multimodulejetpackcompose.presentations.SeachScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nlhd.multimodulejetpackcompose.presentations.SeachScreen.SearchEvent
import com.nlhd.multimodulejetpackcompose.presentations.SeachScreen.SearchViewModel
import com.nlhd.multimodulejetpackcompose.ui.theme.ResponsiveTheme

@Composable
fun SearchBar(searchQuery: String, viewModel: SearchViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                Color.White,
                shape = RoundedCornerShape(ResponsiveTheme.appDimensRes.border)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {viewModel.onEvent(SearchEvent.UpdateSearchQuery(it))},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier
                .height(ResponsiveTheme.appDimensRes.searchHeight)
                .weight(0.65f),

            textStyle = ResponsiveTheme.appTypographyRes.bodySmall,
            maxLines = 1,
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.White)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.onEvent(SearchEvent.Search)
                }
            ),

        )

        Box(
            modifier = Modifier
                .size(width = 0.5.dp, height = ResponsiveTheme.appDimensRes.medium2)
                .background(color = Color.White)
                .weight(0.001f)
        ) {

        }

        Text(
            "Search",
            style = ResponsiveTheme.appTypographyRes.bodyMedium.copy(
                color = Color.White,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.weight(0.25f)
        )

    }
}
