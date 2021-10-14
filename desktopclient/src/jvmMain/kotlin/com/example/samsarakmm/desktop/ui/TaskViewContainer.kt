package com.example.samsarakmm.desktop.ui

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.IStorageService
import com.example.samsarakmm.common.ui.managetaskview.TaskViewLogic
import com.example.samsarakmm.desktop.WindowState
import com.example.samsarakmm.ui.managetaskview.ITaskViewContract
import com.example.samsarakmm.ui.managetaskview.TaskViewEvent
import com.example.samsarakmm.ui.managetaskview.TaskViewModel

class TaskViewContainer(
    var windowState: WindowState,
    var windowArgs: Int
) : ITaskViewContract.Container {

    internal lateinit var logic : BaseViewLogic<TaskViewEvent>

    fun start(storage: IStorageService, vm: TaskViewModel): TaskViewContainer {
        logic = TaskViewLogic(
            this,
            vm,
            storage,
            DispatcherProvider()
        )

        logic.onViewEvent(TaskViewEvent.OnStart)

        return this
    }
    
    override fun goToTaskListActivity() {
        windowArgs = 0
        windowState = WindowState.VIEW_TASK_LIST
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
}