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
    val stateHandler: (WindowState, Int) -> Unit
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
        stateHandler(WindowState.VIEW_TASK_LIST, 0)
    }

    override fun showMessage(message: String) {
        println(message)

    }
}