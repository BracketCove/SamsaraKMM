package com.example.samsarakmm.android.ui.dayview

interface IDayViewContract {
    interface Container {
        fun navigateToHourView(hourInteger: Int)
        fun navigateToTasksView()
        fun showMessage(message: String)
    }
}