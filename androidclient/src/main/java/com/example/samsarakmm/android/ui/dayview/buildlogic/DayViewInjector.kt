package com.example.samsarakmm.android.ui.dayview.buildlogic

import com.example.samsarakmm.android.StorageServiceLocator
import com.example.samsarakmm.android.ui.dayview.DayActivity
import com.example.samsarakmm.ui.dayview.DayViewEvent
import com.example.samsarakmm.ui.dayview.DayViewLogic
import com.example.samsarakmm.ui.dayview.DayViewModel
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider

internal fun DayActivity.buildLogic(
    vm: DayViewModel,
    locator: StorageServiceLocator
): BaseViewLogic<DayViewEvent> {
    return DayViewLogic(
        this,
        vm,
        locator.storage,
        DispatcherProvider()
    )
}