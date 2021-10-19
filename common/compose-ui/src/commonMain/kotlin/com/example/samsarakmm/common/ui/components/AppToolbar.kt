package com.example.samsarakmm.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun AppToolbar(
    title: String,
    iconBack: (@Composable () -> Unit)? = null,
    iconAction: (@Composable () -> Unit)? = null
) {
    val modifier = Modifier
    if (iconBack != null)
        TopAppBar(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )
            },
            navigationIcon = { iconBack.invoke() },
            actions = {
                iconAction?.invoke()
            }
        ) else
        TopAppBar(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            title = {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Start,
                    maxLines = 1
                )
            },
            actions = {
                iconAction?.invoke()
            }
        )
}