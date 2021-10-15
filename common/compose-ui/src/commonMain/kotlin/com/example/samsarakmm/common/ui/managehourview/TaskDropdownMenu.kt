package com.example.samsarakmm.common.ui.managehourview

import androidx.compose.runtime.Composable
import com.example.samsarakmm.common.domain.constants.QUARTER

@Composable
expect fun TaskDropdownMenu(
    taskNames: List<String>,
    showMenu: Boolean,
    onDismiss: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
    menuIndex: Int,
    quarter: QUARTER
)