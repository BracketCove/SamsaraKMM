package com.example.samsarakmm.android.ui.dayview.buildlogic

import android.content.Context
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.database.DatabaseDriverFactory
import com.example.samsarakmm.common.database.StorageService
import com.example.samsarakmm.common.ui.dayview.DayViewLogic
import com.example.samsarakmm.ui.dayview.DayViewEvent
import com.example.samsarakmm.ui.dayview.DayViewModel
import com.example.samsarakmm.ui.dayview.IDayViewContract

fun IDayViewContract.Container.buildLogic(
    vm: DayViewModel
): BaseViewLogic<DayViewEvent> {
    return DayViewLogic(
        this,
        vm,
        StorageService(DatabaseDriverFactory(this as Context)),
        DispatcherProvider()
    )
}