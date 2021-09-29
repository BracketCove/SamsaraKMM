package com.example.samsarakmm.ui.managetaskview


interface ITaskViewContract {
    interface Container {
        fun goToTaskListActivity()
        fun showMessage(message: String)
    }
}