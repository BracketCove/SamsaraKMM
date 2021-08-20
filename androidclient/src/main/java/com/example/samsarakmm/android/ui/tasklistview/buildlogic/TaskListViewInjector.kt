package com.example.samsarakmm.android.ui.tasklistview.buildlogic

import com.example.samsarakmm.android.StorageServiceLocator
import com.example.samsarakmm.android.ui.tasklistview.*
import com.example.samsarakmm.common.BaseViewLogic

internal fun TaskListActivity.buildLogic(
    vm: TaskListViewModel,
    locator: StorageServiceLocator
): BaseViewLogic<TaskListViewEvent> =
    TaskListViewLogic(
        this,
        vm,
        locator.taskStorageImpl
    )