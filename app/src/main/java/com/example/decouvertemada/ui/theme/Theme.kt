package com.example.decouvertemada.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val MadaLightColorScheme = lightColorScheme(
    primary = MadaGreenDeep,
    onPrimary = MadaOnDark,
    secondary = MadaTerracotta,
    onSecondary = MadaOnDark,
    tertiary = MadaGreenSoft,
    onTertiary = MadaOnDark,
    background = MadaBackground,
    onBackground = MadaTextPrimary,
    surface = MadaSurface,
    onSurface = MadaTextPrimary,
    surfaceVariant = MadaSurfaceVariant,
    onSurfaceVariant = MadaTextSecondary,
    outline = MadaSlate
)

/**
 * Thème de l'application "Découverte Mada".
 * Le dynamic color est volontairement désactivé afin de conserver
 * une palette cohérente et peu voyante sur tous les appareils.
 */
@Composable
fun DecouverteMadaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MadaLightColorScheme,
        typography = Typography,
        content = content
    )
}
