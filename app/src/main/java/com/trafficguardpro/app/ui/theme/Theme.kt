package com.trafficguardpro.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = TrafficBlue,
    secondary = TrafficGreen,
    tertiary = TrafficAmber,
    background = TrafficDark,
    surface = ColorPalette.surface,
    onBackground = ColorPalette.onBackground,
    onSurface = ColorPalette.onBackground,
    error = TrafficRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = TrafficBlue,
    secondary = TrafficGreen,
    tertiary = TrafficAmber,
    background = Color(0xFFF6F9FF),
    surface = Color.White,
    onBackground = Color(0xFF101726),
    onSurface = Color(0xFF101726),
    error = TrafficRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

@Composable
fun TrafficGuardProTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
