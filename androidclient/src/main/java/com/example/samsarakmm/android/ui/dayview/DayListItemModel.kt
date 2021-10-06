package com.example.samsarakmm.android.ui.dayview

import androidx.annotation.IntegerRes
import com.example.samsarakmm.android.common.toResId
import com.example.samsarakmm.android.ui.ui.dayview.LIST_ITEM_BLOCK_TYPE
import com.example.samsarakmm.android.ui.ui.dayview.LIST_ITEM_TYPE
import com.example.samsarakmm.common.domain.Day
import com.example.samsarakmm.common.domain.Hour
import com.example.samsarakmm.common.domain.Tasks
import getHourBlockText

/**
 * In order to isolate the logic of generating appropriate data for the view and the adapter,
 * I will create a special kind of ViewModel which will contain the data in a form which simplifies
 * the View binding for the Adapter.
 *
 *
 *
 *
 *
 *
 *
 *
 * See the DayListViewItemMaker class for more details
 */
class DayListItemModel(
    val hourBlockText: String,
    @field:IntegerRes val iconResId: IntArray,
    @field:IntegerRes val backgroundResId: IntArray,
    val taskNames: Array<String>,
    val types: List<LIST_ITEM_BLOCK_TYPE>
)

/**
 * Relative to the number of active QuarterHours and their position, determine the appropriate
 * LIST_ITEM_TYPE for this hour.
 *
 *
 * NOTE: This algorithm assumes that we never have a situation where only one QuarterHour is
 * in an Active state, but that particular QuarterHour is not the first one (i.e. not at
 * index [0] of hour.getQuarters. I have decided to enforce this in the UI itself.
 *
 *
 * 1. Check for invariants first:
 * - Only 1 active task means the whole hour is for that task
 * - Four active tasks is all Quarters
 *
 *
 * - Two active tasks can mean:
 * - Half and Half
 * - Quarter Three Quarter
 * - Three active tasks can be:
 * - Half, Quarter Quarter,
 * - Quarter, Half, Quarter,
 * - Quarter, Quarter Half,
 *
 * @param hour
 * @return
 */
internal fun Hour.getListItemType(): LIST_ITEM_TYPE {
    var activeTasks = 0

    quarters.forEach { q -> if (q.isActive) activeTasks++ }

    //single hour case
    if (activeTasks == 1) return LIST_ITEM_TYPE.FULL_HOUR
    /*
    half and half: 0, 2, active
    Quarter three quarter: 0, 1 active
    Three Quarter Quarter: 0, 3 active
     */if (activeTasks == 2) {
        //skip zero because it's always active
        val one = quarters[1].isActive
        val two = quarters[2].isActive
        val three = quarters[3].isActive
        if (!one && two && !three) return LIST_ITEM_TYPE.HALF_HALF
        if (one && !two && !three) return LIST_ITEM_TYPE.QUARTER_THREE_QUARTER
        if (!one && !two && three) return LIST_ITEM_TYPE.THREE_QUARTER_QUARTER
    }
    if (activeTasks == 3) {
        val first = quarters[0].isActive
        val second = quarters[1].isActive
        val third = quarters[2].isActive
        val fourth = quarters[3].isActive

        //Quarter Half Quarter: 1st is active, 2nd is active, 3rd is inactive, 4th is active
        if (first && second && !third && fourth) return LIST_ITEM_TYPE.QUARTER_HALF_QUARTER
        //Half Quarter Quarter: 1st is active, 2nd is inactive, 3rd is active, 4th is active
        if (first && !second && third && fourth) return LIST_ITEM_TYPE.HALF_QUARTER_QUARTER
        //Quarter Quarter Half: 1st is active, 2nd is active, 3rd is active, 4th is inactive
        if (first && second && third && !fourth) return LIST_ITEM_TYPE.QUARTER_QUARTER_HALF
    }
    return LIST_ITEM_TYPE.QUARTER_QUARTER_QUARTER_QUARTER
}

internal fun Day.toDayListItemModels(tasks: Tasks): List<DayListItemModel> {
    val items = mutableListOf<DayListItemModel>()

    hours.forEach { hour ->

        val icons = mutableListOf<Int>()
        val backgrounds = mutableListOf<Int>()
        val taskNames = mutableListOf<String>()

        hour.quarters.filter { it.isActive }.forEachIndexed { index, quarter ->
            val task = tasks.getTaskById(quarter.taskId)
            icons.add(task!!.taskIcon.toResId())
            backgrounds.add(task.taskColor.toResId())
            taskNames.add(task!!.taskName)
        }

        val types = hour.getListItemType().toBlockList()

        items.add(
            DayListItemModel(
                getHourBlockText(hour.hourInteger, mode),
                icons.toIntArray(),
                backgrounds.toIntArray(),
                taskNames.toTypedArray(),
                types
                )
        )
    }

    return items
}


private fun LIST_ITEM_TYPE.toBlockList() : List<LIST_ITEM_BLOCK_TYPE> {
    return when (this) {
        LIST_ITEM_TYPE.FULL_HOUR -> listOf(LIST_ITEM_BLOCK_TYPE.BLOCK_HOUR)
        LIST_ITEM_TYPE.HALF_HALF -> listOf(
            LIST_ITEM_BLOCK_TYPE.BLOCK_HALF,
            LIST_ITEM_BLOCK_TYPE.BLOCK_HALF
        )
        LIST_ITEM_TYPE.QUARTER_QUARTER_HALF -> listOf(
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_HALF
        )
        LIST_ITEM_TYPE.QUARTER_HALF_QUARTER -> listOf(
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_HALF,
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER
        )
        LIST_ITEM_TYPE.HALF_QUARTER_QUARTER -> listOf(
            LIST_ITEM_BLOCK_TYPE.BLOCK_HALF,
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER
        )
        LIST_ITEM_TYPE.QUARTER_THREE_QUARTER -> listOf(
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_THREE_QUARTER
        )
        LIST_ITEM_TYPE.THREE_QUARTER_QUARTER -> listOf(
            LIST_ITEM_BLOCK_TYPE.BLOCK_THREE_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER
        )
        LIST_ITEM_TYPE.QUARTER_QUARTER_QUARTER_QUARTER -> listOf(
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER,
            LIST_ITEM_BLOCK_TYPE.BLOCK_QUARTER
        )
    }
}