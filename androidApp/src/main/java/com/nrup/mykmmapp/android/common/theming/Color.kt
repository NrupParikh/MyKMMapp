package com.nrup.mykmmapp.android.common.theming


import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF1E80F8)
val Black87 = Color(0xFF18191A)
val Black24 = Color(0xFF242526)
val White = Color(0xFFFFFFFF)
val White87 = Color(0xFFE2E2E2)
val White76 = Color(0xFFF5F5F5)


internal val LightColors = lightColorScheme(
    primary = Blue,
    background = White76,
    onBackground = Black87,
    surface = White,
    onSurface = Black87
)

internal val DarkColors = darkColorScheme(
    primary = Blue,
    background = Black87,
    onBackground = White87,
    surface = Black24,
    onSurface = White87
)