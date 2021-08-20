package com.example.samsarakmm.android.ui.managetaskview.buildlogic

import com.example.samsarakmm.android.StorageServiceLocator
import com.example.samsarakmm.android.ui.managetaskview.TaskActivity
import com.example.samsarakmm.android.ui.managetaskview.TaskViewEvent
import com.example.samsarakmm.android.ui.managetaskview.TaskViewLogic
import com.example.samsarakmm.android.ui.managetaskview.TaskViewModel
import com.example.samsarakmm.common.BaseViewLogic

internal fun TaskActivity.buildLogic(
    vm: TaskViewModel,
    locator: StorageServiceLocator,
): BaseViewLogic<TaskViewEvent> {


    return TaskViewLogic(
        this,
        vm,
        locator.taskStorageImpl
    )
}