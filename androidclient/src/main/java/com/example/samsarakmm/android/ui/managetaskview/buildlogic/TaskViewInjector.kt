package com.example.samsarakmm.android.ui.managetaskview.buildlogic

import android.content.Context
import com.example.samsarakmm.android.ui.dayview.buildlogic.buildLogic
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.IStorageService
import com.example.samsarakmm.common.database.DatabaseDriverFactory
import com.example.samsarakmm.common.database.StorageService
import com.example.samsarakmm.common.ui.managetaskview.TaskViewLogic
import com.example.samsarakmm.ui.managetaskview.ITaskViewContract
import com.example.samsarakmm.ui.managetaskview.TaskViewEvent
import com.example.samsarakmm.ui.managetaskview.TaskViewModel

internal fun ITaskViewContract.Container.buildLogic(
    vm: TaskViewModel
): BaseViewLogic<TaskViewEvent> {


    return TaskViewLogic(
        this,
        vm,
        StorageService(DatabaseDriverFactory(this as Context)),
        DispatcherProvider()
    )
}