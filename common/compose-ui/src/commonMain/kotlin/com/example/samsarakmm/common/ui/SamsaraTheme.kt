package com.example.samsarakmm.common.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.samsarakmm.common.domain.constants.COLOR

internal val primary = Color(0xff333333)
internal val primaryDark = Color(0xff121212)
internal val accent = Color(0xffBB86FC)

internal val darkBlue = Color(0xff1A237E)
internal val burntOrange = Color(0xffD84315)
internal val green = Color(0xff388E3C)
internal val red = Color(0xffB71C1C)
internal val lime = Color(0xff827717)
internal val lightBlue = Color(0xff0288D1)
internal val mauve = Color(0xffBA68C8)
internal val brown = Color(0xff795548)
internal val teal = Color(0xff00897B)

val COLOR.toColor: Color
    get() {
        return when (this) {
            COLOR.DARK_BLUE -> darkBlue
            COLOR.BURNT_ORANGE -> burntOrange
            COLOR.GREEN -> green
            COLOR.DARK_RED -> red
            COLOR.DARK_LIME -> lime
            COLOR.LIGHT_BLUE -> lightBlue
            COLOR.MAUVE -> mauve
            COLOR.BROWN -> brown
            COLOR.TEAL -> teal
            else -> lightBlue
        }
    }


val darkColorPalette = darkColors(
    primary = primary,
    primaryVariant = primaryDark,
    secondary = accent,
    onPrimary = Color.White.copy(alpha = 0.87f)
)

val lightColorPalette = lightColors(
    primary = primary,
    primaryVariant = primaryDark,
    secondary = accent,
    onPrimary = Color.White.copy(alpha = 0.87f)
)

@Composable
fun SamsaraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) darkColorPalette else lightColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}