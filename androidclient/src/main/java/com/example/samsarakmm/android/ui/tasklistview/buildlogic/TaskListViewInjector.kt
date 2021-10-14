package com.example.samsarakmm.android.ui.tasklistview.buildlogic

import android.content.Context
import com.example.samsarakmm.android.ui.dayview.buildlogic.buildLogic
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.DispatcherProvider
import com.example.samsarakmm.common.IStorageService
import com.example.samsarakmm.common.database.DatabaseDriverFactory
import com.example.samsarakmm.common.database.StorageService
import com.example.samsarakmm.common.ui.tasklistview.TaskListViewLogic
import com.example.samsarakmm.ui.tasklistview.ITaskListViewContract
import com.example.samsarakmm.ui.tasklistview.TaskListViewEvent
import com.example.samsarakmm.ui.tasklistview.TaskListViewModel

internal fun ITaskListViewContract.Container.buildLogic(
    vm: TaskListViewModel
): BaseViewLogic<TaskListViewEvent> =
    TaskListViewLogic(
        this,
        vm,
        StorageService(DatabaseDriverFactory(this as Context)),
        DispatcherProvider()
    )