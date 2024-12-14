package com.nlhd.multimodulejetpackcompose.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val extraSmall: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
    val buttonHeight: Dp = 40.dp,
    val logoSize: Dp = 42.dp,
    val border: Dp = 2.dp,
    val searchHeight: Dp = 48.dp
)

val CompactSmallDimens = Dimens(
    extraSmall = 2.dp,
    small1 = 5.dp,
    small2 = 7.dp,
    small3 = 10.dp,
    medium1 = 15.dp,
    medium2 = 20.dp,
    medium3 = 25.dp,
    large = 30.dp,
    buttonHeight = 30.dp,
    logoSize = 36.dp,
    border = 5.dp,
    searchHeight = 50.dp
)

val CompactMediumDimens = Dimens(
    extraSmall = 5.dp,
    small1 = 8.dp,
    small2 = 11.dp,
    small3 = 15.dp,
    medium1 = 20.dp,
    medium2 = 24.dp,
    medium3 = 30.dp,
    large = 36.dp,
    buttonHeight = 35.dp,
    logoSize = 40.dp,
    border = 10.dp,
    searchHeight = 60.dp
)

val MediumDimens = Dimens(
    extraSmall = 10.dp,
    small1 = 15.dp,
    small2 = 18.dp,
    small3 = 20.dp,
    medium1 = 28.dp,
    medium2 = 36.dp,
    medium3 = 42.dp,
    large = 50.dp,
    buttonHeight = 40.dp,
    logoSize = 45.dp
)

val LargeDimens = Dimens(
    extraSmall = 15.dp,
    small1 = 20.dp,
    small2 = 23.dp,
    small3 = 26.dp,
    medium1 = 32.dp,
    medium2 = 40.dp,
    medium3 = 48.dp,
    large = 64.dp,
    buttonHeight = 50.dp,
    logoSize = 60.dp
)