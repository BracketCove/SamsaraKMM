package com.example.samsarakmm.android.ui.managehourview

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.domain.*
import com.example.samsarakmm.domain.constants.HOUR_MODE
import com.example.samsarakmm.domain.constants.Messages.GENERIC_ERROR_MESSAGE
import com.example.samsarakmm.domain.constants.QUARTER

class HourViewLogic(
    private val container: IHourContract.Container,
    private val vm: HourViewModel,
    private val dayStorage: IDayStorage,
    private val taskStorage: ITaskStorage
) : BaseViewLogic<HourViewEvent>() {
    override fun onViewEvent(event: HourViewEvent) {
        when (event) {
            is HourViewEvent.OnStart -> onStart()
            is HourViewEvent.OnDoneClick -> onDone()
            is HourViewEvent.OnQuarterToggled -> onQuarterToggled(event.quarter, event.active)
            is HourViewEvent.OnTaskSelected -> onTaskSelected(event.quarter, event.position)
        }
    }

    private fun onQuarterToggled(quarter: QUARTER, isActive: Boolean) {
        when (quarter) {
            QUARTER.ZERO -> Unit //first is always active
            QUARTER.FIFTEEN -> vm.secondIsActive = isActive
            QUARTER.THIRTY -> vm.thirdIsActive = isActive
            QUARTER.FOURTY_FIVE -> vm.fourthIsActive = isActive
        }
    }

    private fun onTaskSelected(quarter: QUARTER, position: Int) {
        when (quarter) {
            QUARTER.ZERO -> vm.firstSelectedTask = position
            QUARTER.FIFTEEN -> vm.secondSelectedTask = position
            QUARTER.THIRTY -> vm.thirdSelectedTask = position
            QUARTER.FOURTY_FIVE -> vm.fourthSelectedTask = position
        }
    }

    private fun onDone() {
        dayStorage.updateHour(
            Hour(
                arrayOf(
                    QuarterHour(
                        vm.firstSelectedTask,
                        QUARTER.ZERO,
                        true
                    )
                ),
                vm.hourInt
            ),
            {
                container.navigateToDayView()
            },
            {
                container.showMessage(GENERIC_ERROR_MESSAGE)
                container.navigateToDayView()
            }
        )
    }

    private fun onStart() {
        //I give the VM the hour integer and fake data for the rest of the hour, and then
        //grab it from
        val hour = vm.hourInt
        dayStorage.getHourWithMode(
            hour,
            { hour, mode ->
                onHourRetrieved(hour, mode)
            },
            {
                container.showMessage(GENERIC_ERROR_MESSAGE)
                container.navigateToDayView()
            }
        )
    }

    private fun onHourRetrieved(hour: Hour, mode: HOUR_MODE) {
        vm.hourMode = mode
        taskStorage.getTasks(
            { tasks ->
                onTasksRetrieved(
                    tasks,
                    hour
                )
            },
            {
                container.showMessage(GENERIC_ERROR_MESSAGE)
                container.navigateToDayView()
            }
        )
    }

    private fun onTasksRetrieved(tasks: Tasks, hour: Hour) {
        vm.taskNames = tasks.get().map { task -> task.taskName }
        initializeViewData(hour)
    }

    private fun initializeViewData(hour: Hour) {
        //:00
        vm.firstSelectedTask = hour.quarters[0].taskId

        //:15
        vm.secondIsActive = hour.quarters[1].isActive
        vm.secondSelectedTask = hour.quarters[1].taskId

        //:30
        vm.thirdIsActive = hour.quarters[2].isActive
        vm.thirdSelectedTask = hour.quarters[2].taskId

        //:45
        vm.fourthIsActive = hour.quarters[3].isActive
        vm.fourthSelectedTask = hour.quarters[3].taskId
    }

}