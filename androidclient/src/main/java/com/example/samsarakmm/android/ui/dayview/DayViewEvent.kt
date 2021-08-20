package com.example.samsarakmm.android.ui.dayview

/**
 * It models interactions between the View and the Logic class
 */
sealed class DayViewEvent {
    object OnStart : DayViewEvent()
    data class OnHourSelected(val position: Int) : DayViewEvent()
    object OnManageTasksSelected : DayViewEvent()
}