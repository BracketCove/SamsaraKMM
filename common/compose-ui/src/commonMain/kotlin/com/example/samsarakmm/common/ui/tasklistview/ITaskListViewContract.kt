package com.example.samsarakmm.ui.tasklistview

interface ITaskListViewContract {
    interface Container {
        fun showMessage(message: String)
        fun navigateToDayView()
        fun navigateToTaskView(taskId: Int)
    }
}