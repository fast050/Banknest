package com.example.basicbankingapp.ui.homeUI

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.basicbankingapp.R

val montserrat_alternatesBoldFamily = FontFamily(
    Font(R.font.montserrat_alternates_bold , FontWeight.Bold ),
)

val montserrat_alternatesSemiBoldFamily = FontFamily(
    Font(R.font.montserrat_alternates_semibold, FontWeight.Light)
)

val montserrat_alternatesNormalFamily = FontFamily(
    Font(R.font.montserrat_alternates, FontWeight.Bold)
)


val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = montserrat_alternatesBoldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = montserrat_alternatesSemiBoldFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = montserrat_alternatesNormalFamily,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = montserrat_alternatesNormalFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = montserrat_alternatesNormalFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = montserrat_alternatesNormalFamily,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = montserrat_alternatesSemiBoldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = montserrat_alternatesSemiBoldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    labelSmall =  TextStyle(
        fontFamily = montserrat_alternatesSemiBoldFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )
)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

private val DarkColorPalette = darkColorScheme(
    primary = Color.Gray,
    secondary = Color.LightGray
)
private val LightColorPalette = lightColorScheme(
    primary = Color("#1C859F".toColorInt()),
    secondary = Color.DarkGray
)

@Composable
fun ComposeBanknessAppTheme(
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
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}