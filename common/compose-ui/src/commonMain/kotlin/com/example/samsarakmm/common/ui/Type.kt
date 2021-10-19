package com.example.samsarakmm.common.ui

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val typography = Typography(
    h3 = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        color = Color.White.copy(alpha = 0.87f)
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 41.sp,
        color = accent.copy(alpha = 0.87f)
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Thin,
        fontSize = 54.sp,
        fontFamily = FontFamily.Default,
        color = Color.White.copy(alpha = 0.87f)
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        letterSpacing = 1.sp,
        fontFamily = FontFamily.Default,
        color = Color.White.copy(alpha = 0.87f)
    )
)

internal val hourBlockText = TextStyle(
    fontSize = 24.sp,
    color = Color.White.copy(alpha = .86f)
)

val halfAndThreeQuarterHourBlockText = TextStyle(
    fontSize = 20.sp,
    color = Color.White.copy(alpha = .86f)
)

val quarterHourBlockText = TextStyle(
    fontSize = 14.sp,
    color = Color.White.copy(alpha = .86f)
)

val mainTitle = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Light,
    fontSize = 48.sp,
    textAlign = TextAlign.Center
)

fun dropdownText(color: Color) = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 28.sp,
    textAlign = TextAlign.Center,
    color = color
)



