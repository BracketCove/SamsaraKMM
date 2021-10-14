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
    var windowState: WindowState,
    var windowArgs: Int
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
        windowArgs = 0
        windowState = WindowState.VIEW_DAY
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }
}