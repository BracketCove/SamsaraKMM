package com.example.samsarakmm.android.ui.tasklistview

import com.example.samsarakmm.android.ui.tasklistview.ITaskListViewContract
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.domain.ITaskStorage
import com.example.samsarakmm.domain.constants.Messages.GENERIC_ERROR_MESSAGE

class TaskListViewLogic(
    private val view: ITaskListViewContract.Container,
    private val vm: ITaskListViewContract.ViewModel,
    private val storage: ITaskStorage
) : BaseViewLogic<TaskListViewEvent>() {
    override fun onViewEvent(event: TaskListViewEvent) {
        when (event) {
            is TaskListViewEvent.OnStart -> onStart()
            is TaskListViewEvent.OnListItemSelected -> onItemSelected(event.position)
            is TaskListViewEvent.OnBackPressed -> onBackPressed()
        }
    }

    private fun onBackPressed() {
        view.navigateToDayView()
    }

    private fun onItemSelected(position: Int) {
        val id = vm.tasks.get()[position].taskId
        view.navigateToTaskView(id)
    }

    private fun onStart() {
        storage.getTasks(
            { tasks ->
                vm.tasks = tasks
            },
            {
                view.showMessage(GENERIC_ERROR_MESSAGE)
                view.navigateToDayView()
            }
        )
    }
}