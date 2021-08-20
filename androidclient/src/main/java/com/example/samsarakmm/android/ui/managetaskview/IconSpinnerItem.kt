package com.example.samsarakmm.android.ui.managetaskview

import android.content.Context
import java.util.*

internal class IconSpinnerItem(val itemText: String, val itemIconResId: Int) {

    companion object {
        const val NUMBER_OF_ICONS = 9

        @JvmStatic
        fun getItems(context: Context?): List<IconSpinnerItem> {
            val list: MutableList<IconSpinnerItem> = ArrayList()
            for (index in 0 until NUMBER_OF_ICONS) {
//                list.add(
//                    IconSpinnerItem(
//                        ICON_NAMES.get(index),
//                        getResIdFromEnum(
//                            context!!,
//                            ICON.values()[index]
//                        )
//                    )
//                )
            }
            return list
        }
    }
}