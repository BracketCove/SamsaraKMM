package com.example.samsarakmm.android.ui.tasklistview.buildlogic

import com.example.samsarakmm.android.StorageServiceLocator
import com.example.samsarakmm.android.ui.tasklistview.TaskListActivity
import com.example.samsarakmm.android.ui.tasklistview.TaskListViewEvent
import com.example.samsarakmm.android.ui.tasklistview.TaskListViewLogic
import com.example.samsarakmm.android.ui.tasklistview.TaskListViewModel
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.ProductionDispatcherProvider

internal fun TaskListActivity.buildLogic(
    vm: TaskListViewModel,
    locator: StorageServiceLocator
): BaseViewLogic<TaskListViewEvent> =
    TaskListViewLogic(
        this,
        vm,
        locator.storage,
        ProductionDispatcherProvider()
    )