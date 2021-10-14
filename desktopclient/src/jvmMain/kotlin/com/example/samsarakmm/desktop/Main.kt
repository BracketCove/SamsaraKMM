package com.example.samsarakmm.desktop


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.samsarakmm.common.database.DatabaseDriverFactory
import com.example.samsarakmm.common.database.StorageService
import com.example.samsarakmm.common.ui.SamsaraTheme
import com.example.samsarakmm.common.ui.dayview.DayViewScreen
import com.example.samsarakmm.desktop.ui.DayViewContainer
import com.example.samsarakmm.desktop.ui.HourViewContainer
import com.example.samsarakmm.desktop.ui.TaskListViewContainer
import com.example.samsarakmm.desktop.ui.TaskViewContainer
import com.example.samsarakmm.ui.dayview.DayViewModel
import com.example.samsarakmm.common.ui.managehourview.HourViewModel
import com.example.samsarakmm.ui.managetaskview.TaskViewModel
import com.example.samsarakmm.ui.tasklistview.TaskListViewModel

/**
 *
 */
fun main() = application {

    val storageService = StorageService(
        DatabaseDriverFactory()
    )

    var windowState by remember {
        mutableStateOf(WindowState.VIEW_DAY)
    }

    var windowArgs by remember {
        mutableStateOf(0)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Todo"
    ) {
        SamsaraTheme {
            when (windowState) {
                WindowState.VIEW_DAY -> {
                    val vm = DayViewModel()
                    val container = buildDayFeature(
                        windowState,
                        windowArgs,
                        vm,
                        storageService
                    )

                    DayViewScreen(
                        container.logic::onViewEvent,
                        vm
                    )
                }
                WindowState.VIEW_TASK_LIST -> {
                    val vm = TaskListViewModel()
                    val container = buildTasksFeature(
                        windowState,
                        windowArgs,
                        vm,
                        storageService
                    )
                    TaskListScreen(
                        container.logic::onViewEvent,
                        vm
                    )
                }
                WindowState.VIEW_MANAGE_TASK -> TODO()
                WindowState.VIEW_HOUR -> TODO()
            }
        }

    }
}

fun buildDayFeature(windowState: WindowState,
                    windowArgs: Int,
                    vm: DayViewModel,
                    storageService: StorageService): DayViewContainer =
    DayViewContainer(
        windowState,
        windowArgs
    ).start(
        storageService,
        vm
    )

fun buildTaskFeature(windowState: WindowState,
                     windowArgs: Int,
                     vm: TaskViewModel,
                     storageService: StorageService): TaskViewContainer =
    TaskViewContainer(
        windowState,
        windowArgs
    ).start(
        storageService,
        vm
    )

fun buildTasksFeature(windowState: WindowState,
                    windowArgs: Int,
                    vm: TaskListViewModel,
                    storageService: StorageService): TaskListViewContainer =
    TaskListViewContainer(
        windowState,
        windowArgs
    ).start(
        storageService,
        vm
    )

fun buildHourFeature(windowState: WindowState,
                     windowArgs: Int,
                     vm: HourViewModel,
                     storageService: StorageService): HourViewContainer =
    HourViewContainer(
        windowState,
        windowArgs
    ).start(
        storageService,
        vm
    )


enum class WindowState {
    VIEW_DAY,
    VIEW_TASK_LIST,
    VIEW_MANAGE_TASK,
    VIEW_HOUR
}