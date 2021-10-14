package com.example.samsarakmm.common.ui.managetaskview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.samsarakmm.common.domain.constants.ICON
import com.example.samsarakmm.common.ui.dropdownText
import com.example.samsarakmm.common.ui.managehourview.TaskDropdownMenu
import com.example.samsarakmm.common.ui.toImageVector
import com.example.samsarakmm.ui.managetaskview.TaskViewEvent

@Composable
actual fun TaskIconDropdownMenu(
    iconNames: List<String>,
    icons: List<ICON>,
    showMenu: Boolean,
    onDismiss: () -> Unit,
    onMenuItemClick: (ICON) -> Unit,
    menuIndex: Int
) {
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = onDismiss,
    ) {
        icons.forEachIndexed { index, _ ->
            DropdownMenuItem(
                onClick = { onMenuItemClick(icons[index]) },
                modifier = Modifier
                    .wrapContentSize()
                    .background(MaterialTheme.colors.primaryVariant)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        contentDescription = iconNames[index],
                        imageVector = icons[index].toImageVector,
                        tint = Color.White.copy(alpha = .87f),
                        modifier = Modifier.size(36.dp)
                    )

                    Text(
                        text = iconNames[index],
                        style = dropdownText(Color.White.copy(alpha = .87f)),
                        modifier = Modifier.padding(start = 8.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                }
            }
        }
    }
}