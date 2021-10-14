package com.example.samsarakmm.android.ui.managehourview.buildlogic

import android.content.Context
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.database.DatabaseDriverFactory
import com.example.samsarakmm.common.database.StorageService
import com.example.samsarakmm.common.ui.managehourview.HourViewLogic
import com.example.samsarakmm.ui.managehourview.HourViewEvent
import com.example.samsarakmm.common.ui.managehourview.HourViewModel
import com.example.samsarakmm.ui.managehourview.IHourContract


internal fun IHourContract.Container.buildLogic(
    vm: HourViewModel
): BaseViewLogic<HourViewEvent> {
    return HourViewLogic(
        this,
        vm,
        StorageService(DatabaseDriverFactory(this as Context)),
        DispatcherProvider()
    )
}