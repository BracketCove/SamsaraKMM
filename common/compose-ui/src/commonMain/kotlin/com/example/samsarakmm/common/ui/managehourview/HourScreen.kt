package com.example.samsarakmm.common.ui.managehourview

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.samsarakmm.common.STRING_DROPDOWN
import com.example.samsarakmm.common.STRING_MANAGE_HOUR
import com.example.samsarakmm.common.domain.Task
import com.example.samsarakmm.common.domain.constants.QUARTER
import com.example.samsarakmm.common.ui.components.AppToolbar
import com.example.samsarakmm.common.ui.components.LoadingScreen
import com.example.samsarakmm.common.ui.halfAndThreeQuarterHourBlockText
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
        taskNames = viewModel.taskNames,
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
        taskNames = viewModel.taskNames,
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
        taskNames = viewModel.taskNames,
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
        taskNames = viewModel.taskNames,
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
    taskNames: List<String>,
    showBottomDivider: Boolean = true,
    showToggle: Boolean = true,
    quarter: QUARTER,
    eventHandler: (HourViewEvent) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier
                    .wrapContentSize()
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

                HourDropdown(
                    modifier = Modifier
                        .wrapContentWidth(),
                    eventHandler = eventHandler,
                    selectedTask = selectedTask,
                    taskNames = taskNames,
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
                    color = if (showBottomDivider) Color.White.copy(alpha = .46f)
                    else Color.Transparent
                )
                .height(1.dp),
        )
    }


}

@Composable
fun HourDropdown(
    modifier: Modifier,
    eventHandler: (HourViewEvent) -> Unit,
    selectedTask: Int,
    taskNames: List<String>,
    quarter: QUARTER
) {
    var showMenu by remember { mutableStateOf(false) }

    var menuIndex by remember {
        mutableStateOf(
            selectedTask
        )
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.clickable(
                onClick = { showMenu = true }
            )
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
                    .padding(start = 32.dp),
                text = taskNames[menuIndex],
                style = halfAndThreeQuarterHourBlockText
            )

            Icon(
                contentDescription = STRING_DROPDOWN,
                imageVector = Icons.Outlined.ArrowDropDown,
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .size(48.dp)
                    .rotate(
                        animateFloatAsState(
                            if (!showMenu) 0f else 180f,
                        ).value
                    )
                    .align(Alignment.CenterVertically)
            )

            TaskDropdownMenu(
                taskNames = taskNames,
                showMenu,
                { showMenu = false },
                { index ->
                    menuIndex = index
                    showMenu = false
                    eventHandler.invoke(
                        HourViewEvent.OnTaskSelected(
                            quarter,
                            index
                        )
                    )
                },
                menuIndex,
                quarter
            )
           ///on menu item click

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