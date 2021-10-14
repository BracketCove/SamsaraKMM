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
    val stateHandler: (WindowState, Int) -> Unit
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
        println(message)
    }

    override fun navigateToDayView() {
        stateHandler(WindowState.VIEW_DAY, 0)
    }

    override fun navigateToTaskView(taskId: Int) {
        stateHandler(WindowState.VIEW_MANAGE_TASK, taskId)
    }

}