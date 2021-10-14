package com.example.samsarakmm.common.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.samsarakmm.common.domain.constants.ICON


val ICON.toImageVector: ImageVector
    get() {
        return when (this) {
            ICON.FREE_TIME -> Icons.Default.Accessibility
            ICON.BREAK -> Icons.Default.FreeBreakfast
            ICON.STUDY -> Icons.Default.School
            ICON.WORK -> Icons.Default.Work
            ICON.EXERCISE -> Icons.Default.FitnessCenter
            ICON.MENTAL_CULTIVATION -> Icons.Default.SelfImprovement
            ICON.EAT -> Icons.Default.RestaurantMenu
            ICON.SLEEP -> Icons.Default.Hotel
            ICON.SHOP -> Icons.Default.ShoppingCart
        }
    }