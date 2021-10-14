package com.example.samsarakmm.common.ui.managetaskview

import androidx.compose.runtime.Composable
import com.example.samsarakmm.common.domain.constants.ICON
import com.example.samsarakmm.common.domain.constants.QUARTER
import com.example.samsarakmm.ui.managehourview.HourViewEvent

@Composable
expect fun TaskIconDropdownMenu(
    iconNames: List<String>,
    icons: List<ICON>,
    showMenu: Boolean,
    onDismiss: () -> Unit,
    onMenuItemClick: (ICON) -> Unit,
    menuIndex: Int
)