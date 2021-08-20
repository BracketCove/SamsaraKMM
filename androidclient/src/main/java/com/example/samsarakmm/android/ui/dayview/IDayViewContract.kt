package com.example.samsarakmm.android.ui.dayview

import com.example.samsarakmm.domain.Day
import com.example.samsarakmm.domain.Tasks

interface IDayViewContract {
    interface Container {
        fun navigateToHourView(hourInteger: Int)
        fun navigateToTasksView()
        fun showMessage(message: String)
    }

    interface ViewModel {
        var day: Day
        var tasks: Tasks
    }
}