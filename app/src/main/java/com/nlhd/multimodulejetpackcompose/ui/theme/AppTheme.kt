package com.nlhd.multimodulejetpackcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class AppTypography(
    val largeTitle: TextStyle = TextStyle.Default,
    val title: TextStyle = TextStyle.Default,
    val subTitle: TextStyle = TextStyle.Default,
    val body: TextStyle = TextStyle.Default
)

data class AppColor(
    val blueUi: Color = Color(0xFFB2EFF8),
    val blackUi: Color = Color(0xFF222A35)
)

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography()
}

val LocalAppColor = staticCompositionLocalOf {
    AppColor()
}


@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val typography = AppTypography(
        largeTitle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),
        title = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),
        subTitle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        ),
        body = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    )

    val color = if (isDarkTheme) AppColor() else AppColor(blueUi = Color.White)

    CompositionLocalProvider(LocalAppTypography provides typography, LocalAppColor provides  color) {
        content.invoke()
    }
}

object AppTheme {
    val appTypography: AppTypography
    @Composable
    get() = LocalAppTypography.current

    val appColor: AppColor
    @Composable
    get() = LocalAppColor.current
}