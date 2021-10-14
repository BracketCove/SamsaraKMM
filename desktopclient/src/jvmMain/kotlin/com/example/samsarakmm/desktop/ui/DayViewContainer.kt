package com.example.samsarakmm.desktop.ui

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.IStorageService
import com.example.samsarakmm.common.ui.dayview.DayViewLogic
import com.example.samsarakmm.desktop.WindowState
import com.example.samsarakmm.ui.dayview.DayViewEvent
import com.example.samsarakmm.ui.dayview.DayViewModel
import com.example.samsarakmm.ui.dayview.IDayViewContract

class DayViewContainer(
    var windowState: WindowState,
    var windowArgs: Int
) : IDayViewContract.Container {

    internal lateinit var logic : BaseViewLogic<DayViewEvent>

    fun start(storage: IStorageService, vm: DayViewModel): DayViewContainer {
        logic = DayViewLogic(
            this,
            vm,
            storage,
            DispatcherProvider()
        )

        logic.onViewEvent(DayViewEvent.OnStart)

        return this
    }

    override fun navigateToHourView(hourInteger: Int) {
        windowArgs = hourInteger
        windowState = WindowState.VIEW_HOUR
    }

    override fun navigateToTasksView() {
        windowArgs = 0
        windowState = WindowState.VIEW_TASK_LIST
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
}