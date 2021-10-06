package com.example.samsarakmm.android.ui.managehourview.buildlogic

import com.example.samsarakmm.android.StorageServiceLocator
import com.example.samsarakmm.android.ui.managehourview.HourActivity
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.ui.managehourview.HourViewLogic
import com.example.samsarakmm.ui.managehourview.HourViewEvent
import com.example.samsarakmm.ui.managehourview.HourViewModel


internal fun HourActivity.buildLogic(
    vm: HourViewModel,
    locator: StorageServiceLocator
): BaseViewLogic<HourViewEvent> {
    return HourViewLogic(
        this,
        vm,
        locator.storage,
        DispatcherProvider()
    )
}