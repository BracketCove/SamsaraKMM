package com.example.samsarakmm.desktop.ui

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.IStorageService
import com.example.samsarakmm.common.ui.tasklistview.TaskListViewLogic
import com.example.samsarakmm.desktop.WindowState
import com.example.samsarakmm.ui.tasklistview.ITaskListViewContract
import com.example.samsarakmm.ui.tasklistview.TaskListViewEvent
import com.example.samsarakmm.ui.tasklistview.TaskListViewModel

class TaskListViewContainer(
    var windowState: WindowState,
    var windowArgs: Int
) : ITaskListViewContract.Container {
    
    internal lateinit var logic : BaseViewLogic<TaskListViewEvent>

    fun start(storage: IStorageService, vm: TaskListViewModel): TaskListViewContainer {
        logic = TaskListViewLogic(
            this,
            vm,
            storage,
            DispatcherProvider()
        )

        logic.onViewEvent(TaskListViewEvent.OnStart)

        return this
    }
    
    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }

    override fun navigateToDayView() {
        windowArgs = 0
        windowState = WindowState.VIEW_DAY
    }

    override fun navigateToTaskView(taskId: Int) {
        windowArgs = taskId
        windowState = WindowState.VIEW_MANAGE_TASK
    }

}