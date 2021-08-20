package com.example.samsarakmm.android.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

private const val TITLE = "Day View"

@Composable
fun AppToolbar(
    modifier: Modifier,
    title: String,
    iconBack: (@Composable () -> Unit)? = null,
    iconAction: (@Composable () -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        title = {
            Row {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )
            }

        },
        navigationIcon = { iconBack?.invoke() },
        actions = {
            iconAction?.invoke()
        }
    )
}