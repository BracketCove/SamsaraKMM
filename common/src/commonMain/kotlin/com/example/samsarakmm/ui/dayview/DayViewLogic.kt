package com.example.samsarakmm.ui.dayview

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.domain.Day
import com.example.samsarakmm.domain.Tasks
import com.example.samsarakmm.domain.constants.Messages
import com.example.samsarakmm.storage.IStorageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

//across significant architectural boundaries, we want to depend on abstractions instead of concrete classes
//an easy way to achieve this in Java and Kotlin, is to use Interfaces, abstract class
class DayViewLogic(
    private val container: IDayViewContract.Container,
    private val vm: DayViewModel,
    private val storage: IStorageService,
    private val dispatcher: DispatcherProvider
) : BaseViewLogic<DayViewEvent>(), CoroutineScope {

    init {
        //allows cancellation
        jobTracker = Job()
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher.provideUIContext() + jobTracker

    override fun onViewEvent(event: DayViewEvent) {
        when (event) {
            is DayViewEvent.OnStart -> onStart()
            is DayViewEvent.OnHourSelected -> onHourSelected(event.position)
            is DayViewEvent.OnManageTasksSelected -> onManageTaskSelected()
        }
    }

    private fun onManageTaskSelected() {
        container.navigateToTasksView()
    }

    private fun onHourSelected(hourInteger: Int) {
        container.navigateToHourView(hourInteger)
    }

    private fun onStart() = launch {
        storage.getDay(
            { day ->
                getTasks(day)
            },
            { error ->
                container.showMessage(Messages.GENERIC_ERROR_MESSAGE)
            }
        )
    }

    private fun getTasks(day: Day) = launch {
        storage.getTasks(
            { tasks ->
                bindData(day, tasks)
            },
            {
                container.showMessage(Messages.GENERIC_ERROR_MESSAGE)
            }
        )
    }

    private fun bindData(dayResult: Day, taskResult: Tasks) {
        vm.day = dayResult
        vm.tasks = taskResult
        vm.isLoading = false
    }
}