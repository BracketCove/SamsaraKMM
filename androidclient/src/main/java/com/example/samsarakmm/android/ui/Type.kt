package com.example.samsarakmm.android.ui

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
        fontSize = 41.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 3.sp
    )
)

internal val hourBlockText = TextStyle(
    fontSize = 24.sp,
    color = Color.White.copy(alpha = .86f)
)

internal val halfAndThreeQuarterHourBlockText = TextStyle(
    fontSize = 20.sp,
    color = Color.White.copy(alpha = .86f)
)

internal val quarterHourBlockText = TextStyle(
    fontSize = 14.sp,
    color = Color.White.copy(alpha = .86f)
)

fun dropdownText(color: Color) = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 28.sp,
    textAlign = TextAlign.Center,
    color = color
)



