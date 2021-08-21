package com.example.samsarakmm.android.ui.dayview

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.samsarakmm.android.R
import com.example.samsarakmm.android.ui.components.AppToolbar
import com.example.samsarakmm.android.ui.components.LoadingScreen
import com.example.samsarakmm.android.ui.halfAndThreeQuarterHourBlockText
import com.example.samsarakmm.android.ui.hourBlockText
import com.example.samsarakmm.android.ui.quarterHourBlockText
import com.example.samsarakmm.android.ui.ui.dayview.LIST_ITEM_BLOCK_TYPE

@Composable
fun DayViewScreen(
    eventHandler: (DayViewEvent) -> Unit,
    viewModel: DayViewModel
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
        DayViewContent(
            eventHandler = eventHandler,
            viewModel = viewModel
        )
    }
}

@Composable
fun DayViewContent(
    eventHandler: (DayViewEvent) -> Unit,
    viewModel: DayViewModel
) {
    Column {
        AppToolbar(
            modifier = Modifier,
            title = stringResource(id = R.string.tasks),
            iconAction = { DayViewIconTasks(eventHandler = eventHandler) }
        )

        HoursOfDay(
            eventHandler,
            viewModel
        )


    }
}

@Composable
fun DayViewIconTasks(
    eventHandler: (DayViewEvent) -> Unit
) {
    Icon(
        painterResource(id = R.drawable.ic_list_white_24dp),
        modifier = Modifier
            .clickable(onClick = { eventHandler.invoke(DayViewEvent.OnManageTasksSelected) })
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .height(36.dp),
        contentDescription = null
    )
}

@Composable
fun HoursOfDay(
    eventHandler: (DayViewEvent) -> Unit,
    viewModel: DayViewModel
) {
    Box(Modifier.background(MaterialTheme.colors.primaryVariant)) {
        //TODO convert it lazycolumn if performance is an issue
        val dayListItems = viewModel.day.toDayListItemModels(viewModel.tasks)

        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(
                    enabled = true, state = ScrollState(0)
                )
        ) {
            Spacer(Modifier.height(4.dp))

            dayListItems.forEachIndexed { index, item ->
                DayListItem(item, index, eventHandler)
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun DayListItem(
    listItemModel: DayListItemModel,
    hourInteger: Int,
    eventHandler: (DayViewEvent) -> Unit
) {
    val itemSize = 90.dp
    Row(
        Modifier
            .fillMaxWidth()
            .size(itemSize)
            .clickable { eventHandler(DayViewEvent.OnHourSelected(hourInteger)) },
    ) {
        DayListItemHourBlock(
            listItemModel.hourBlockText,
            size = itemSize
        )

        Spacer(modifier = Modifier.width(2.dp))

        DayListTaskBlock(
            listItemModel,
            height = itemSize
        )

    }
}

@Composable
fun DayListItemHourBlock(
    hourBlockText: String,
    size: Dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(4.dp)
            ),
        contentAlignment = Alignment.Center,

        ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = hourBlockText,
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
fun DayListTaskBlock(
    listItemModel: DayListItemModel,
    height: Dp
) {
    Column(
        Modifier
            .height(height)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        listItemModel.types.forEachIndexed { index, type ->
            when (type) {
                LIST_ITEM_BLOCK_TYPE.BLOCK_HOUR -> HourTaskBlock(
                    taskIcon = listItemModel.iconResId[index],
                    taskBackground = listItemModel.backgroundResId[index],
                    taskName = listItemModel.taskNames[index]
                )
                LIST_ITEM_BLOCK_TYPE.BLOCK_THREE_QUARTER -> {
                    ThreeQuarterTaskBlock(
                        taskIcon = listItemModel.iconResId[index],
                        taskBackground = listItemModel.backgroundResId[index],
                        taskName = listItemModel.taskNames[index]
                    )

                }
                LIST_ITEM_BLOCK_TYPE.BLOCK_HALF -> HalfHourTaskBlock(
                    taskIcon = listItemModel.iconResId[index],
                    taskBackground = listItemModel.backgroundResId[index],
                    taskName = listItemModel.taskNames[index]
                )
                LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER -> QuarterHourTaskBlock(
                    taskIcon = listItemModel.iconResId[index],
                    taskBackground = listItemModel.backgroundResId[index],
                    taskName = listItemModel.taskNames[index]
                )
            }

            if (index != listItemModel.types.lastIndex) Spacer(Modifier.height(2.dp))
        }
    }
}

private val BLOCK_ICON_PADDING_START = 8.dp

@Composable
fun QuarterHourTaskBlock(
    taskIcon: Int,
    taskBackground: Int,
    taskName: String
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(21.dp)
            .background(
                colorResource(id = taskBackground),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        TaskBlockIcon(
            modifier = Modifier
                .size(21.dp)
                .align(Alignment.CenterStart)
                .padding(
                    start = BLOCK_ICON_PADDING_START
                ),
            taskIcon,
            taskName
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = taskName,
            style = quarterHourBlockText
        )
    }
}

@Composable
fun HalfHourTaskBlock(
    taskIcon: Int,
    taskBackground: Int,
    taskName: String
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(43.dp)
            .background(
                colorResource(id = taskBackground),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        TaskBlockIcon(
            modifier = Modifier
                .size(33.dp)
                .align(Alignment.CenterStart)
                .padding(
                    start = BLOCK_ICON_PADDING_START
                ),
            taskIcon,
            taskName
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = taskName,
            style = halfAndThreeQuarterHourBlockText
        )
    }
}

@Composable
fun ThreeQuarterTaskBlock(
    taskIcon: Int,
    taskBackground: Int,
    taskName: String
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(
                colorResource(id = taskBackground),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        TaskBlockIcon(
            modifier = Modifier
                .size(38.dp)
                .align(Alignment.CenterStart)
                .padding(
                    start = BLOCK_ICON_PADDING_START
                ),
            taskIcon,
            taskName
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = taskName,
            style = halfAndThreeQuarterHourBlockText
        )
    }
}

@Composable
fun HourTaskBlock(
    taskIcon: Int,
    taskBackground: Int,
    taskName: String
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                colorResource(id = taskBackground),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        TaskBlockIcon(
            modifier = Modifier
                .fillMaxHeight()
                .width(44.dp)
                .align(Alignment.CenterStart)
                .padding(
                    start = BLOCK_ICON_PADDING_START
                ),
            taskIcon,
            taskName
        )


        Text(
            modifier = Modifier.align(Alignment.Center),
            text = taskName,
            style = hourBlockText
        )
    }
}

@Composable
fun TaskBlockIcon(
    modifier: Modifier,
    id: Int,
    taskName: String
) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = id),
        contentDescription = taskName,
        tint = Color.White.copy(alpha = .86f),
    )
}