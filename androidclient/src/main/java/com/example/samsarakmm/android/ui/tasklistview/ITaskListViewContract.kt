package com.example.samsarakmm.android.ui.tasklistview

import com.example.samsarakmm.domain.Tasks

interface ITaskListViewContract {
    interface Container {
        fun showMessage(message: String)
        fun navigateToDayView()
        fun navigateToTaskView(taskId: Int)
    }

    interface ViewModel {
        var tasks: Tasks
    }
}