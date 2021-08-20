package com.example.samsarakmm.android.ui.managetaskview


interface ITaskViewContract {
    interface Container {
        fun goToTaskListActivity()
        fun showMessage(message: String)
    }
}