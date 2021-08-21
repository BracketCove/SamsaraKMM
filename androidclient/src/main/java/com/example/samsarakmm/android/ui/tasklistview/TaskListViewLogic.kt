package com.example.samsarakmm.android.ui.tasklistview

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.domain.constants.Messages.GENERIC_ERROR_MESSAGE
import com.example.samsarakmm.storage.IStorageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TaskListViewLogic(
    private val view: ITaskListViewContract.Container,
    private val vm: TaskListViewModel,
    private val storage: IStorageService,
    private val dispatcher: DispatcherProvider
) : BaseViewLogic<TaskListViewEvent>(), CoroutineScope {

    init {
        //allows cancellation
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

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

    private fun onStart() = launch {
        storage.getTasks(
            { tasks ->
                vm.tasks = tasks
                vm.isLoading = false
            },
            {
                view.showMessage(GENERIC_ERROR_MESSAGE)
                view.navigateToDayView()
            }
        )
    }
}