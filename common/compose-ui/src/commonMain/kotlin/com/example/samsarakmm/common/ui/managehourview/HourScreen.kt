package com.example.samsarakmm.common.ui.managehourview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.samsarakmm.common.STRING_MANAGE_HOUR
import com.example.samsarakmm.common.domain.Task
import com.example.samsarakmm.common.domain.constants.QUARTER
import com.example.samsarakmm.common.ui.*
import com.example.samsarakmm.common.ui.components.AppToolbar
import com.example.samsarakmm.common.ui.components.LoadingScreen
import com.example.samsarakmm.common.ui.components.TaskPickerButton
import com.example.samsarakmm.ui.managehourview.HourViewEvent
import getHourToggleViewFormattedText


@Composable
fun HourScreen(
    viewModel: HourViewModel,
    eventHandler: (HourViewEvent) -> Unit
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
        HourViewContent(
            eventHandler = eventHandler,
            viewModel = viewModel
        )
    }
}

@Composable
fun HourViewContent(
    viewModel: HourViewModel,
    eventHandler: (HourViewEvent) -> Unit
) {
    Column(
        Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxSize()
    ) {
        AppToolbar(
            modifier = Modifier,
            title = STRING_MANAGE_HOUR,
            iconAction = { HourDoneIcon(eventHandler = eventHandler) }
        )

        //TODO: I'm pretty sure I should rig up a list of subscriber functions in the
        // ViewModel and then abstract them out of the Quarters
        FirstQuarter(viewModel = viewModel, eventHandler = eventHandler)
        SecondQuarter(viewModel = viewModel, eventHandler = eventHandler)
        ThirdQuarter(viewModel = viewModel, eventHandler = eventHandler)
        FourthQuarter(viewModel = viewModel, eventHandler = eventHandler)
    }
}


@Composable
fun FirstQuarter(
    viewModel: HourViewModel,
    eventHandler: (HourViewEvent) -> Unit
) {
    var firstTaskIndex by remember {
        mutableStateOf(viewModel.firstSelectedTask)
    }

    viewModel.subFirstSelectedTaskIndex = { newIndex ->
        firstTaskIndex = newIndex
    }

    QuarterHourBlock(
        active = true,
        selectedTask = firstTaskIndex,
        timeText = getHourToggleViewFormattedText(
            QUARTER.ZERO,
            viewModel.hourInt,
            viewModel.hourMode
        ),
        tasks = viewModel.tasks,
        quarter = QUARTER.ZERO,
        eventHandler = eventHandler,
        showToggle = false
    )
}

@Composable
fun SecondQuarter(
    viewModel: HourViewModel,
    eventHandler: (HourViewEvent) -> Unit
) {
    //second
    var secondTaskIndex by remember {
        mutableStateOf(viewModel.secondSelectedTask)
    }

    viewModel.subSecondSelectedTaskIndex = { newIndex ->
        secondTaskIndex = newIndex
    }

    var secondIsActive by remember {
        mutableStateOf(viewModel.secondIsActive)
    }

    viewModel.subSecondIsActive = { isActive ->
        secondIsActive = isActive
    }

    QuarterHourBlock(
        active = secondIsActive,
        selectedTask = secondTaskIndex,
        timeText = getHourToggleViewFormattedText(
            QUARTER.FIFTEEN,
            viewModel.hourInt,
            viewModel.hourMode
        ),
        tasks = viewModel.tasks,
        quarter = QUARTER.FIFTEEN,
        eventHandler = eventHandler
    )
}

@Composable
fun ThirdQuarter(
    viewModel: HourViewModel,
    eventHandler: (HourViewEvent) -> Unit
) {
//third
    var thirdTaskIndex by remember {
        mutableStateOf(viewModel.thirdSelectedTask)
    }

    viewModel.subThirdSelectedTaskIndex = { newIndex ->
        thirdTaskIndex = newIndex
    }

    var thirdIsActive by remember {
        mutableStateOf(viewModel.thirdIsActive)
    }

    viewModel.subThirdIsActive = { isActive ->
        thirdIsActive = isActive
    }

    QuarterHourBlock(
        active = thirdIsActive,
        selectedTask = thirdTaskIndex,
        timeText = getHourToggleViewFormattedText(
            QUARTER.THIRTY,
            viewModel.hourInt,
            viewModel.hourMode
        ),
        tasks = viewModel.tasks,
        quarter = QUARTER.THIRTY,
        eventHandler = eventHandler
    )

}

@Composable
fun FourthQuarter(
    viewModel: HourViewModel,
    eventHandler: (HourViewEvent) -> Unit
) {
    var fourthTaskIndex by remember {
        mutableStateOf(viewModel.fourthSelectedTask)
    }

    viewModel.subFourthSelectedTaskIndex = { newIndex ->
        fourthTaskIndex = newIndex
    }

    var fourthIsActive by remember {
        mutableStateOf(viewModel.fourthIsActive)
    }

    viewModel.subFourthIsActive = { isActive ->
        fourthIsActive = isActive
    }

    QuarterHourBlock(
        active = fourthIsActive,
        selectedTask = fourthTaskIndex,
        timeText = getHourToggleViewFormattedText(
            QUARTER.FOURTY_FIVE,
            viewModel.hourInt,
            viewModel.hourMode
        ),
        tasks = viewModel.tasks,
        quarter = QUARTER.FOURTY_FIVE,
        eventHandler = eventHandler,
        showBottomDivider = false
    )
}

@Composable
fun QuarterHourBlock(
    active: Boolean,
    selectedTask: Int,
    timeText: String,
    tasks: List<Task>,
    showBottomDivider: Boolean = true,
    showToggle: Boolean = true,
    quarter: QUARTER,
    eventHandler: (HourViewEvent) -> Unit
) {
    Column {
        Row {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp
                    )
            ) {
                Text(
                    modifier = Modifier,
                    text = timeText,
                    style = MaterialTheme.typography.h4.copy(
                        color = if (active) MaterialTheme.colors.secondary
                        else MaterialTheme.colors.onPrimary
                    )
                )

                HourTaskPicker(
                    modifier = Modifier
                        .wrapContentWidth(),
                    eventHandler = eventHandler,
                    tasks = tasks,
                    quarter = quarter
                )

            }

            Switch(
                checked = active,
                onCheckedChange = {
                    eventHandler(
                        HourViewEvent.OnQuarterToggled(
                            quarter,
                            it
                        )
                    )
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.secondary,
                    uncheckedThumbColor = MaterialTheme.colors.onPrimary
                ),
                enabled = showToggle,
            )
        }

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        Divider(
            Modifier
                .background(
                    color = if (showBottomDivider) Color.White
                    else Color.Transparent
                )
                .height(2.dp),
        )
    }


}

@Composable
fun HourTaskPicker(
    eventHandler: (HourViewEvent) -> Unit,
    modifier: Modifier,
    tasks: List<Task>,
    quarter: QUARTER
) {
    BoxWithConstraints(modifier) {
        val size = with(LocalDensity.current) { (constraints.maxWidth / 3).toDp() }

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(size)
            ) {
                (0..2).forEach {
                    TaskPickerButton(
                        task = tasks[it],
                        modifier = Modifier.size(size).clickable(
                            onClick = {
                                eventHandler(
                                    HourViewEvent.OnTaskSelected(
                                        quarter,
                                        tasks[it].taskId
                                    )
                                )
                            }
                        )
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(size)
            ) {
                (3..5).forEach {
                    TaskPickerButton(
                        task = tasks[it],
                        modifier = Modifier.size(size).clickable(
                            onClick = {
                                eventHandler(
                                    HourViewEvent.OnTaskSelected(
                                        quarter,
                                        tasks[it].taskId
                                    )
                                )
                            }
                        )
                    )
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(size)
            ) {
                (6..8).forEach {
                    TaskPickerButton(
                        task = tasks[it],
                        modifier = Modifier.size(size).clickable(
                            onClick = {
                                eventHandler(
                                    HourViewEvent.OnTaskSelected(
                                        quarter,
                                        tasks[it].taskId
                                    )
                                )
                            }
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun HourDoneIcon(
    eventHandler: (HourViewEvent) -> Unit
) {
    Icon(
        imageVector = Icons.Default.Done,
        modifier = Modifier
            .clickable(onClick = { eventHandler.invoke(HourViewEvent.OnDoneClick) })
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(36.dp),
        contentDescription = null
    )
}