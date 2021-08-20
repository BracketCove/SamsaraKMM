package com.example.samsarakmm.android.ui.managehourview

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.samsarakmm.android.R
import com.example.samsarakmm.android.common.getHourToggleViewFormattedText
import com.example.samsarakmm.android.ui.components.AppToolbar
import com.example.samsarakmm.android.ui.dropdownText
import com.example.samsarakmm.android.ui.halfAndThreeQuarterHourBlockText
import com.example.samsarakmm.domain.constants.QUARTER


@Composable
fun HourScreen(
    viewModel: HourViewModel,
    onEvent: (HourViewEvent) -> Unit
) {
    Column(
        Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxSize()
    ) {
        AppToolbar(
            modifier = Modifier,
            title = stringResource(id = R.string.manage_hour),
            iconAction = { HourDoneIcon(eventHandler = onEvent) }
        )

        //first
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
            onEvent = onEvent,
            showToggle = false
        )

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
            onEvent = onEvent
        )

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
            onEvent = onEvent
        )

        //fourth
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
            onEvent = onEvent,
            showBottomDivider = false
        )
    }
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
    onEvent: (HourViewEvent) -> Unit
) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp
            )
    ) {

        val (timeDisplay,
            toggle,
            dropdown,
            spacer,
            divider
        ) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(timeDisplay) {
                    start.linkTo(parent.start)
                }
                .padding(),
            text = timeText,
            style = MaterialTheme.typography.h4.copy(
                color = if (active) MaterialTheme.colors.secondary
                else MaterialTheme.colors.onPrimary
            )
        )

        Switch(
            modifier = Modifier.constrainAs(toggle) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            },
            checked = active,
            onCheckedChange = {
                onEvent(
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

        HourDropdown(
            modifier = Modifier
                .constrainAs(dropdown) {
                    top.linkTo(timeDisplay.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .wrapContentWidth(),
            onEvent = onEvent,
            selectedTask = selectedTask,
            taskNames = taskNames,
            quarter = quarter
        )

        Spacer(modifier = Modifier
            .height(4.dp)
            .constrainAs(spacer) {
                top.linkTo(dropdown.bottom)
            }
        )

        Divider(
            Modifier
                .constrainAs(divider) {
                    top.linkTo(spacer.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(
                    color = if (showBottomDivider) Color.White
                    else Color.Transparent
                )
                .height(2.dp),
        )
    }


}

@Composable
fun HourDropdown(
    modifier: Modifier,
    onEvent: (HourViewEvent) -> Unit,
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
                contentDescription = stringResource(R.string.dropdown),
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

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
            ) {
                taskNames.forEachIndexed { index, _ ->
                    DropdownMenuItem(
                        onClick = {
                            menuIndex = index
                            showMenu = false
                            onEvent.invoke(
                                HourViewEvent.OnTaskSelected(
                                    quarter,
                                    index
                                )
                            )
                        },
                        modifier = Modifier
                            .wrapContentSize()
                            .background(MaterialTheme.colors.primaryVariant)
                    ) {
                        Text(
                            text = taskNames[index],
                            style = dropdownText(Color.White.copy(alpha = .87f)),
                            modifier = Modifier.padding(start = 8.dp),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
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
        painterResource(id = R.drawable.ic_done_white_24dp),
        modifier = Modifier
            .clickable(onClick = { eventHandler.invoke(HourViewEvent.OnDoneClick) })
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(36.dp),
        contentDescription = null
    )
}