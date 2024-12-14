package com.nlhd.multimodulejetpackcompose.presentations.commonConponent

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nlhd.multimodulejetpackcompose.ui.theme.AppTheme
import com.nlhd.multimodulejetpackcompose.ui.theme.ResponsiveTheme
import com.nlhd.multimodulejetpackcompose.ui.theme.ScreenOrientation
import com.nlhd.multimodulejetpackcompose.ui.theme.paddingSystemMedium
import com.nlhd.multimodulejetpackcompose.ui.theme.paddingSystemSmall

@Composable
fun TopBar(screen: String) {
    if (ScreenOrientation == Configuration.ORIENTATION_PORTRAIT) {
        Text(
            screen,
            style = ResponsiveTheme.appTypographyRes.titleLarge.copy(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = paddingSystemMedium)
        )
    } else {
        Text(
            screen,
            style = ResponsiveTheme.appTypographyRes.titleSmall.copy(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = paddingSystemMedium)
        )
    }


    Box(modifier = Modifier
        .fillMaxWidth()
        .height(2.dp)
        .background(color = AppTheme.appColor.blueUi)
    )
    Spacer(modifier = Modifier.height(ResponsiveTheme.appDimensRes.small1))

}