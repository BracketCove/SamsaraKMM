package com.example.samsarakmm.common.ui.managetaskview

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.samsarakmm.common.STRING_DROPDOWN
import com.example.samsarakmm.common.STRING_MANAGE_TASK
import com.example.samsarakmm.common.STRING_NAME
import com.example.samsarakmm.common.domain.constants.COLOR
import com.example.samsarakmm.common.domain.constants.ICON
import com.example.samsarakmm.common.domain.constants.ICON_NAMES
import com.example.samsarakmm.common.ui.*
import com.example.samsarakmm.common.ui.components.AppToolbar
import com.example.samsarakmm.common.ui.components.LoadingScreen
import com.example.samsarakmm.common.ui.managehourview.TaskDropdownMenu
import com.example.samsarakmm.ui.managehourview.HourViewEvent
import com.example.samsarakmm.ui.managetaskview.TaskViewEvent
import com.example.samsarakmm.ui.managetaskview.TaskViewModel


@Composable
fun TaskScreen(
    viewModel: TaskViewModel,
    eventHandler: (TaskViewEvent) -> Unit
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
        TaskViewContent(
            eventHandler = eventHandler,
            viewModel = viewModel
        )
    }
}

@Composable
fun TaskViewContent(
    viewModel: TaskViewModel,
    eventHandler: (TaskViewEvent) -> Unit
) {
    Column(
        Modifier.background(
            MaterialTheme.colors.primaryVariant
        ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppToolbar(
            title = STRING_MANAGE_TASK,
            iconAction = { TaskDoneIcon(eventHandler) }
        )

        Spacer(Modifier.height(16.dp))

        var name by remember {
            mutableStateOf(viewModel.getTaskName())
        }

        viewModel.subTaskName = { newName ->
            name = newName
        }

        NameField(value = name, eventHandler = eventHandler)

        Spacer(Modifier.height(16.dp))

        var icon by remember {
            mutableStateOf(viewModel.getIcon())
        }

        viewModel.subIcon = { newIcon ->
            icon = newIcon
        }

        TaskDropdown(
            eventHandler = eventHandler,
            selectedIcon = icon,
            icons = ICON.values().toList(),
            names = ICON_NAMES
        )

        Spacer(Modifier.height(16.dp))

        //color display
        var color by remember {
            mutableStateOf(viewModel.getColor())
        }

        viewModel.subColor = { newColor ->
            color = newColor
        }

        ColorDisplay(
            color = color
        )

        Spacer(Modifier.height(32.dp))

        ColorPicker(
            eventHandler,
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = MaterialTheme.colors.primary.copy(alpha = 0.50f))
        )
    }
}

@Composable
fun NameField(
    value: String,
    eventHandler: (TaskViewEvent) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        value = value,
        onValueChange = { newText ->
            if (newText.length < 25) eventHandler(TaskViewEvent.OnNameChanged(newText))
        },
        label = {
            Text(
                STRING_NAME,
                color = MaterialTheme.colors.secondary
            )
        },
        maxLines = 1,
        textStyle = quarterHourBlockText
    )
}

@Composable
fun ColorPicker(
    eventHandler: (TaskViewEvent) -> Unit,
    modifier: Modifier
) {
    BoxWithConstraints(modifier) {
        val size = with(LocalDensity.current) { (constraints.maxWidth).toDp() }
        val circleSize = with(LocalDensity.current) { (constraints.maxWidth*.3f).toDp() }
        Column(
            Modifier.size(size)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(.33f)
            ) {
                ColorPickerButton(COLOR.DARK_BLUE, circleSize, eventHandler)
                ColorPickerButton(COLOR.BURNT_ORANGE, circleSize, eventHandler)
                ColorPickerButton(COLOR.GREEN, circleSize, eventHandler)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(.33f)
            ) {
                ColorPickerButton(COLOR.DARK_RED, circleSize, eventHandler)
                ColorPickerButton(COLOR.DARK_LIME, circleSize, eventHandler)
                ColorPickerButton(COLOR.LIGHT_BLUE, circleSize, eventHandler)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(.33f)
            ) {
                ColorPickerButton(COLOR.MAUVE, circleSize, eventHandler)
                ColorPickerButton(COLOR.BROWN, circleSize, eventHandler)
                ColorPickerButton(COLOR.TEAL, circleSize, eventHandler)
            }
        }
    }
}

@Composable
fun ColorPickerButton(
    color: COLOR,
    size: Dp,
    eventHandler: (TaskViewEvent) -> Unit
) {
    Button(
        modifier = Modifier
            .size(size)
            .padding(32.dp),
        onClick = { eventHandler(TaskViewEvent.OnColorSelected(color)) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color.toColor
        ),
        shape = CircleShape
    ) {

    }
}


@Composable
fun ColorDisplay(
    color: COLOR
) {
    //For some reason applying padding to child box doesn't work
    Row(Modifier.padding(start = 32.dp, end = 32.dp)) {
        Box(
            Modifier
                .height(64.dp)
                .fillMaxWidth()
                .background(
                    color = color.toColor,
                    shape = RoundedCornerShape(2.dp)
                ),
            contentAlignment = Alignment.Center

        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                style = quarterHourBlockText,
                text = color.name.replace('_', ' '),
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * initialIndex is the index of the Icon currently associated with this task
 *
 */
@Composable
fun TaskDropdown(
    eventHandler: (TaskViewEvent) -> Unit,
    selectedIcon: ICON,
    icons: List<ICON>,
    names: List<String>
) {
    val initialIndex = selectedIcon.ordinal

    var showMenu by remember { mutableStateOf(false) }
    var menuIndex by remember {
        mutableStateOf(
            initialIndex
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.clickable(
                onClick = { showMenu = true }
            )
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(36.dp),
                imageVector = selectedIcon.toImageVector,
                contentDescription = names[menuIndex],
                tint = Color.White.copy(alpha = .86f),
            )

            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
                    .padding(start = 32.dp),
                text = names[menuIndex],
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

            TaskIconDropdownMenu(
                iconNames = names,
                icons = icons,
                showMenu,
                { showMenu = false },
                { icon ->
                    showMenu = false
                    eventHandler.invoke(
                        TaskViewEvent.OnIconSelected(
                            icon
                        )
                    )
                },
                menuIndex
            )
        }
    }
}

@Composable
fun TaskDoneIcon(
    eventHandler: (TaskViewEvent) -> Unit
) {
    Icon(
       imageVector = Icons.Default.Done,
        modifier = Modifier
            .clickable(onClick = { eventHandler.invoke(TaskViewEvent.OnDoneClick) })
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(36.dp),
        contentDescription = null
    )
}
