package com.example.samsarakmm.android.ui.managetaskview

import com.example.samsarakmm.domain.constants.COLOR
import com.example.samsarakmm.domain.constants.ICON

/**
 * It models interactions between the View and the Logic class
 */
sealed class TaskViewEvent {
    data class OnColorSelected(val color: COLOR) : TaskViewEvent()
    object OnDoneClick : TaskViewEvent()
    data class OnNameChanged(val name: String) : TaskViewEvent()
    data class OnIconSelected(val icon: ICON) : TaskViewEvent()
    object OnStart : TaskViewEvent()
}