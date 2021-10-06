package com.example.samsarakmm.ui.tasklistview

sealed class TaskListViewEvent {
    object OnStart: TaskListViewEvent()
    data class OnListItemSelected(val position: Int): TaskListViewEvent()
    object OnBackPressed: TaskListViewEvent()
}