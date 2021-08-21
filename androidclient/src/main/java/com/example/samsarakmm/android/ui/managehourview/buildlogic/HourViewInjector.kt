package com.example.samsarakmm.android.ui.managehourview.buildlogic

import com.example.samsarakmm.android.StorageServiceLocator
import com.example.samsarakmm.android.ui.managehourview.HourActivity
import com.example.samsarakmm.android.ui.managehourview.HourViewEvent
import com.example.samsarakmm.android.ui.managehourview.HourViewLogic
import com.example.samsarakmm.android.ui.managehourview.HourViewModel
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.ProductionDispatcherProvider


internal fun HourActivity.buildLogic(
    vm: HourViewModel,
    locator: StorageServiceLocator
) : BaseViewLogic<HourViewEvent> {
       return HourViewLogic(
           this,
           vm,
           locator.storage,
           ProductionDispatcherProvider()
       )
}