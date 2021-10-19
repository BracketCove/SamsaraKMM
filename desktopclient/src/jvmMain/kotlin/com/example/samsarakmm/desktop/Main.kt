package com.example.samsarakmm.desktop


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.example.samsarakmm.common.database.DatabaseDriverFactory
import com.example.samsarakmm.common.database.StorageService
import com.example.samsarakmm.common.ui.SamsaraTheme
import com.example.samsarakmm.common.ui.dayview.DayViewScreen
import com.example.samsarakmm.common.ui.managehourview.HourScreen
import com.example.samsarakmm.common.ui.managehourview.HourViewModel
import com.example.samsarakmm.common.ui.managetaskview.TaskScreen
import com.example.samsarakmm.common.ui.tasklistview.TaskListScreen
import com.example.samsarakmm.desktop.ui.DayViewContainer
import com.example.samsarakmm.desktop.ui.HourViewContainer
import com.example.samsarakmm.desktop.ui.TaskListViewContainer
import com.example.samsarakmm.desktop.ui.TaskViewContainer
import com.example.samsarakmm.ui.dayview.DayViewModel
import com.example.samsarakmm.ui.managetaskview.TaskViewModel
import com.example.samsarakmm.ui.tasklistview.TaskListViewModel
import java.awt.Toolkit

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

    val screenSize = Toolkit.getDefaultToolkit().screenSize

    Window(
        state = rememberWindowState(
            width = (screenSize.width*.40).dp,
            height = (screenSize.height*.95).dp
        ),
        onCloseRequest = ::exitApplication,
        title = "Todo",
        resizable = false,
    ) {

        SamsaraTheme {
            when (windowState) {
                WindowState.VIEW_DAY -> {
                    val vm = DayViewModel()
                    val container = buildDayFeature(
                        { newState: WindowState, arg: Int ->
                            windowState = newState
                            windowArgs = arg
                        },
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
                        { newState: WindowState, arg: Int ->
                            windowState = newState
                            windowArgs = arg
                        },
                        vm,
                        storageService
                    )
                    TaskListScreen(
                        vm,
                        container.logic::onViewEvent
                    )
                }
                WindowState.VIEW_MANAGE_TASK -> {
                    val vm = TaskViewModel(windowArgs)
                    val container = buildTaskFeature(
                        { newState: WindowState, arg: Int ->
                            windowState = newState
                            windowArgs = arg
                        },
                        vm,
                        storageService
                    )
                    TaskScreen(
                        vm,
                        container.logic::onViewEvent
                    )
                }
                WindowState.VIEW_HOUR -> {
                    val vm = HourViewModel(windowArgs)
                    val container = buildHourFeature(
                        { newState: WindowState, arg: Int ->
                            windowState = newState
                            windowArgs = arg
                        },
                        vm,
                        storageService
                    )
                    HourScreen(
                        vm,
                        container.logic::onViewEvent
                    )
                }
            }
        }

    }
}


fun buildDayFeature(
    stateHandler: (WindowState, Int) -> Unit,
    vm: DayViewModel,
    storageService: StorageService
): DayViewContainer =
    DayViewContainer(
        stateHandler
    ).start(
        storageService,
        vm
    )

fun buildTaskFeature(
    stateHandler: (WindowState, Int) -> Unit,
    vm: TaskViewModel,
    storageService: StorageService
): TaskViewContainer =
    TaskViewContainer(
        stateHandler
    ).start(
        storageService,
        vm
    )

fun buildTasksFeature(
    stateHandler: (WindowState, Int) -> Unit,
    vm: TaskListViewModel,
    storageService: StorageService
): TaskListViewContainer =
    TaskListViewContainer(
        stateHandler
    ).start(
        storageService,
        vm
    )

fun buildHourFeature(
    stateHandler: (WindowState, Int) -> Unit,
    vm: HourViewModel,
    storageService: StorageService
): HourViewContainer =
    HourViewContainer(
        stateHandler
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