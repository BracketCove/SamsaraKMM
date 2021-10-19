package com.example.samsarakmm.common.ui.tasklistview

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.samsarakmm.common.domain.Task
import com.example.samsarakmm.common.domain.constants.ICON
import com.example.samsarakmm.common.ui.components.AppToolbar
import com.example.samsarakmm.common.ui.components.LoadingScreen
import com.example.samsarakmm.common.ui.toImageVector
import com.example.samsarakmm.ui.tasklistview.TaskListViewEvent
import com.example.samsarakmm.ui.tasklistview.TaskListViewModel

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel,
    eventHandler: (TaskListViewEvent) -> Unit
) {
    var showLoading by remember {
        mutableStateOf(
            true
        )
    }

    viewModel.subIsLoading = {
        showLoading = it
    }

    if (showLoading) {
        LoadingScreen()
    } else {
        TaskListViewContent(
            eventHandler = eventHandler,
            viewModel = viewModel
        )
    }
}

@Composable
fun TaskListViewContent(
    eventHandler: (TaskListViewEvent) -> Unit,
    viewModel: TaskListViewModel
) {
    BoxWithConstraints {
        val size = with(LocalDensity.current) { (constraints.maxWidth / 2).toDp() }
        //take half for left column
        Column {
            AppToolbar(
                title =  "Tasks",
                iconBack = { TaskListIconBack(eventHandler = eventHandler) }
            )

            Row(Modifier.fillMaxSize()) {

                TaskListColumn(
                    size = size,
                    tasks = viewModel.tasks.getFirstHalf(),
                    eventHandler = eventHandler
                )

                TaskListColumn(
                    size = size,
                    tasks = viewModel.tasks.getSecondHalf(),
                    eventHandler = eventHandler
                )
            }

        }
    }
}

@Composable
fun TaskListColumn(
    size: Dp,
    tasks: List<Task>,
    eventHandler: (TaskListViewEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .width(size)
            .verticalScroll(
                enabled = true,
                state = ScrollState(0)
            )
    ) {

        tasks.forEach {
            TaskListItem(
                color = Color(it.taskColor.rgb),
                icon = it.taskIcon,
                text = it.taskName,
                height = size,
                it.taskId,
                eventHandler
            )
        }
    }
}

@Composable
fun TaskListItem(
    color: Color,
    icon: ICON,
    text: String,
    height: Dp,
    taskId: Int,
    eventHandler: (TaskListViewEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = color)
            .fillMaxWidth()
            .height(height = height)
            .clickable(
                onClick = {
                    eventHandler.invoke(
                        TaskListViewEvent.OnListItemSelected(
                            taskId
                        )
                    )
                }
            )

    ) {
        Column(
            Modifier.align(
                Alignment.Center
            )
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .align(CenterHorizontally),
                imageVector = icon.toImageVector,
                contentDescription = null,
                tint = Color.White.copy(alpha = .86f),
            )

            Text(
                text = text,
                color = Color.White,
                style = typography.h4
            )
        }
    }
}

@Composable
fun TaskListIconBack(
    eventHandler: (TaskListViewEvent) -> Unit
) {
    Icon(
        imageVector = Icons.Default.NavigateBefore,
        modifier = Modifier
            .clickable(onClick = { eventHandler.invoke(TaskListViewEvent.OnBackPressed) })
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(36.dp),
        contentDescription = null
    )
}