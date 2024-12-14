package com.nlhd.multimodulejetpackcompose.ui.theme

import android.app.Activity
import androidx.compose.material3.Typography
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.nlhd.multimodulejetpackcompose.MainActivity

val LocalAppDimensRes = staticCompositionLocalOf {
    CompactSmallDimens
}

val LocalAppTypographyRes = staticCompositionLocalOf {
    CompactSmallTypography
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ResponsiveTheme(
    activity: Activity = LocalContext.current as MainActivity,
    content: @Composable () -> Unit
) {
    val window = calculateWindowSizeClass(activity = activity)
    val config = LocalConfiguration.current

    var typography = CompactSmallTypography
    var dimens = CompactSmallDimens

    when (window.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (config.screenWidthDp <= 360) {
                dimens = CompactSmallDimens
                typography = CompactSmallTypography
            } else if (config.screenWidthDp < 599) {
                dimens = CompactMediumDimens
                typography = CompactMediumTypography
            }
            else {
                dimens = MediumDimens
                typography = MediumTypography
            }
        }
        WindowWidthSizeClass.Medium -> {
            dimens = MediumDimens
            typography = CompactMediumTypography
        }
        WindowWidthSizeClass.Expanded -> {
            dimens = LargeDimens
            typography = LargeTypography
        }
    }

    CompositionLocalProvider(LocalAppDimensRes provides dimens, LocalAppTypographyRes provides typography) {
        content()
    }
}

object ResponsiveTheme {
    val appDimensRes: Dimens
        @Composable
        get() = LocalAppDimensRes.current

    val appTypographyRes: Typography
        @Composable
        get() = LocalAppTypographyRes.current

}

val ScreenOrientation
    @Composable
    get() = LocalConfiguration.current.orientation