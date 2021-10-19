package com.example.samsarakmm.common.ui.managehourview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.samsarakmm.common.domain.constants.QUARTER
import com.example.samsarakmm.common.ui.dropdownText

@Composable
actual fun TaskDropdownMenu(
    taskNames: List<String>,
    showMenu: Boolean,
    onDismiss: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
    menuIndex: Int,
    quarter: QUARTER
) {
    DropdownMenu(
        modifier = Modifier.background(MaterialTheme.colors.primaryVariant),
        expanded = showMenu,
        onDismissRequest = onDismiss,
    ) {
        taskNames.forEachIndexed { index, _ ->
            DropdownMenuItem(
                onClick = { onMenuItemClick(index) },
                modifier = Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colors.primaryVariant)
            ) {
                Text(
                    text = taskNames[index],
                    style = dropdownText(Color.White.copy(alpha = .87f)),
                    modifier = Modifier.padding(start = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}