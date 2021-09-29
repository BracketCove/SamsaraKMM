package com.example.samsarakmm.ui.dayview

interface IDayViewContract {
    interface Container {
        fun navigateToHourView(hourInteger: Int)
        fun navigateToTasksView()
        fun showMessage(message: String)
    }
}