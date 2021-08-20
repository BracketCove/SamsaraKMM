package com.example.samsarakmm.android.ui.tasklistview

import com.example.samsarakmm.android.ui.tasklistview.ITaskListViewContract
import com.example.samsarakmm.domain.Tasks

class TaskListViewModel : ITaskListViewContract.ViewModel {
    override lateinit var tasks: Tasks
}