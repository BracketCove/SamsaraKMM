package com.example.samsarakmm.desktop.ui

import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.IStorageService
import com.example.samsarakmm.common.ui.managehourview.HourViewLogic
import com.example.samsarakmm.desktop.WindowState
import com.example.samsarakmm.ui.managehourview.HourViewEvent
import com.example.samsarakmm.common.ui.managehourview.HourViewModel
import com.example.samsarakmm.ui.managehourview.IHourContract

class HourViewContainer(
    val stateHandler: (WindowState, Int) -> Unit
) : IHourContract.Container {

    internal lateinit var logic : BaseViewLogic<HourViewEvent>

    fun start(storage: IStorageService, vm: HourViewModel): HourViewContainer {
        logic = HourViewLogic(
            this,
            vm,
            storage,
            DispatcherProvider()
        )

        logic.onViewEvent(HourViewEvent.OnStart)

        return this
    }
    override fun navigateToDayView() {
        stateHandler(WindowState.VIEW_DAY, 0)
    }

    override fun showMessage(message: String) {
        println(message)
    }
}