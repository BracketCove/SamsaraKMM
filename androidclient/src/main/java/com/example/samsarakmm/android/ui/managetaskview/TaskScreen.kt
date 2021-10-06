package com.example.samsarakmm.android.ui.managetaskview

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.samsarakmm.android.R
import com.example.samsarakmm.android.common.toResId
import com.example.samsarakmm.android.ui.components.AppToolbar
import com.example.samsarakmm.android.ui.components.LoadingScreen
import com.example.samsarakmm.android.ui.dropdownText
import com.example.samsarakmm.android.ui.halfAndThreeQuarterHourBlockText
import com.example.samsarakmm.android.ui.quarterHourBlockText
import com.example.samsarakmm.common.domain.constants.COLOR
import com.example.samsarakmm.common.domain.constants.ICON
import com.example.samsarakmm.common.domain.constants.ICON_NAMES
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
            modifier = Modifier,
            title = stringResource(id = R.string.manage_task),
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
            color = color.toResId()
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
                stringResource(id = R.string.name),
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
        val size = with(LocalDensity.current) { (constraints.maxWidth / 3).toDp() }

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(size)
            ) {
                ColorPickerButton(COLOR.DARK_BLUE, size, eventHandler)
                ColorPickerButton(COLOR.BURNT_ORANGE, size, eventHandler)
                ColorPickerButton(COLOR.GREEN, size, eventHandler)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(size)
            ) {
                ColorPickerButton(COLOR.DARK_RED, size, eventHandler)
                ColorPickerButton(COLOR.DARK_LIME, size, eventHandler)
                ColorPickerButton(COLOR.LIGHT_BLUE, size, eventHandler)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(size)
            ) {
                ColorPickerButton(COLOR.MAUVE, size, eventHandler)
                ColorPickerButton(COLOR.BROWN, size, eventHandler)
                ColorPickerButton(COLOR.TEAL, size, eventHandler)
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
            .padding(24.dp),
        onClick = { eventHandler(TaskViewEvent.OnColorSelected(color)) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = color.toResId())
        ),
        shape = CircleShape
    ) {

    }
}


@Composable
fun ColorDisplay(
    color: Int
) {
    //For some reason applying padding to child box doesn't work
    Row(Modifier.padding(start = 32.dp, end = 32.dp)) {
        Box(
            Modifier
                .height(64.dp)
                .fillMaxWidth()
                .background(
                    color = colorResource(id = color),
                    shape = RoundedCornerShape(2.dp)
                )
                ,
            contentAlignment = Alignment.Center

        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                style = quarterHourBlockText,
                text = stringResource(id = R.string.color),
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
                painter = painterResource(id = selectedIcon.toResId()),
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
                icons.forEachIndexed { index, _ ->
                    DropdownMenuItem(
                        onClick = {
                            menuIndex = index
                            showMenu = false
                            eventHandler.invoke(
                                TaskViewEvent.OnIconSelected(
                                    icons[index]
                                )
                            )
                        },
                        modifier = Modifier
                            .wrapContentSize()
                            .background(MaterialTheme.colors.primaryVariant)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Icon(
                                contentDescription = names[index],
                                painter = painterResource(icons[index].toResId()),
                                tint = Color.White.copy(alpha = .87f),
                                modifier = Modifier.size(36.dp)
                            )

                            Text(
                                text = names[index],
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
}

@Composable
fun TaskDoneIcon(
    eventHandler: (TaskViewEvent) -> Unit
) {
    Icon(
        painterResource(id = R.drawable.ic_done_white_24dp),
        modifier = Modifier
            .clickable(onClick = { eventHandler.invoke(TaskViewEvent.OnDoneClick) })
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(36.dp),
        contentDescription = null
    )
}
