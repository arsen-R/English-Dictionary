package com.example.english_dictionary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Dark,
    primaryVariant = Purple700,
    secondary = LightBlue,
    onPrimary = Color.White
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Purple700,
    secondary = LightBlue,
    background = Color.White,
    onPrimary = Color.Black,
    surface = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun EnglishDictionaryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUIController = rememberSystemUiController()
    val colors = if (darkTheme) {
        systemUIController.setSystemBarsColor(Dark)
        systemUIController.setNavigationBarColor(MaterialTheme.colors.background)
        DarkColorPalette
    } else {
        systemUIController.setSystemBarsColor(Color.White)
        systemUIController.setNavigationBarColor(Color.White)
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}