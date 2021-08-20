package com.example.samsarakmm.android.ui.dayview

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.domain.Day
import com.example.samsarakmm.domain.IDayStorage
import com.example.samsarakmm.domain.ITaskStorage
import com.example.samsarakmm.domain.Tasks
import com.example.samsarakmm.domain.constants.Messages

//across significant architectural boundaries, we want to depend on abstractions instead of concrete classes
//an easy way to achieve this in Java and Kotlin, is to use Interfaces, abstract class
class DayViewLogic(
    private val container: IDayViewContract.Container,
    private val vm: IDayViewContract.ViewModel,
    private val dayStorage: IDayStorage,
    private val taskStorage: ITaskStorage
) : BaseViewLogic<DayViewEvent>() {
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

    private fun onStart() {
        dayStorage.getDay(
            { day ->
                getTasks(day)
            },
            { error ->
                container.showMessage(Messages.GENERIC_ERROR_MESSAGE)
            }
        )
    }

    private fun getTasks(day: Day) {
        taskStorage.getTasks(
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
    }
}