package com.kk.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = Snow,
    onPrimary = BurntSienna,
    secondary = DimGray,
    background = EerieBlack
)

private val LightColorPalette = lightColorScheme(
    primary = EerieBlack,
    onPrimary = BurntSienna,
    secondary = DimGray,
    background = Snow
)

@Composable
fun KnowledgeKnockoutTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = KkTypography,
        content = content
    )
}