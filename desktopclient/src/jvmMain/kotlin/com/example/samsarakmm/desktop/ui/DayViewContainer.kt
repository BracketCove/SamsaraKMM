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
    val stateHandler: (WindowState, Int) -> Unit
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
        stateHandler(WindowState.VIEW_HOUR, hourInteger)
    }

    override fun navigateToTasksView() {
        stateHandler(WindowState.VIEW_TASK_LIST, 0)
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
}