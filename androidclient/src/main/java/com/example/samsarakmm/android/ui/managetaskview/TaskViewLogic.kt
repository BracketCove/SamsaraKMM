package com.example.samsarakmm.android.ui.managetaskview

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.domain.Task
import com.example.samsarakmm.domain.constants.COLOR
import com.example.samsarakmm.domain.constants.ICON
import com.example.samsarakmm.domain.constants.Messages.GENERIC_ERROR_MESSAGE
import com.example.samsarakmm.storage.IStorageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class TaskViewLogic
/**
 * Why skip Use Cases/Interactors/Transaction Scripts and just call the Repository directly?
 * Because this feature is so simple that adding in the domain layer (Use Case...etc.) is
 * not worth the effort.
 */(
    private val container: ITaskViewContract.Container,
    private val vm: TaskViewModel,
    private val storage: IStorageService,
    private val dispatcher: DispatcherProvider
) : BaseViewLogic<TaskViewEvent>(), CoroutineScope {

    init {
        //allows cancellation
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

    override fun onViewEvent(event: TaskViewEvent) {
        when (event) {
            is TaskViewEvent.OnStart -> onStart()
            is TaskViewEvent.OnColorSelected -> onColorSelected(event.color)
            is TaskViewEvent.OnDoneClick -> updateStorage()
            is TaskViewEvent.OnNameChanged -> onNameChanged(event.name)
            is TaskViewEvent.OnIconSelected -> onIconSelected(event.icon)
        }
    }

    private fun onNameChanged(name: String) = vm.setTaskNameField(name)


    private fun onStart() = launch {
        storage.getTask(
            vm.taskId,
            { task ->
                vm.setColor(task.taskColor)
                vm.setIcon(task.taskIcon)
                vm.setTaskNameField(task.taskName)
                vm.isLoading = false
            },
            {
                container.showMessage(GENERIC_ERROR_MESSAGE)
                container.goToTaskListActivity()
            }
        )
    }

    private fun onIconSelected(icon: ICON) = vm.setIcon(icon)

    private fun updateStorage() = launch {
        val update = Task(
            vm.taskId,
            vm.getTaskName(),
            vm.getIcon(),
            vm.getColor()
        )
        storage.updateTask(
            update,
            {
                container.goToTaskListActivity()
            },
            {
                container.showMessage(GENERIC_ERROR_MESSAGE)
                container.goToTaskListActivity()
            }

        )
    }

    private fun onColorSelected(color: COLOR) = vm.setColor(color)
}