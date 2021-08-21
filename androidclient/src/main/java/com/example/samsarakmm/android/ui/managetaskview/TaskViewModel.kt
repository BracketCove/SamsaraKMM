package com.example.samsarakmm.android.ui.managetaskview

import com.example.samsarakmm.common.BaseViewModel
import com.example.samsarakmm.domain.constants.COLOR
import com.example.samsarakmm.domain.constants.ICON

/**
 * A data store (cache) for front end session data
 */
class TaskViewModel(val taskId: Int): BaseViewModel() {
    private lateinit var name: String
    private lateinit var color: COLOR
    private lateinit var icon: ICON

    internal var subColor: ((COLOR) -> Unit)? = null
    internal var subIcon: ((ICON) -> Unit)? = null
    internal var subTaskName: ((String) -> Unit)? = null

    internal fun setTaskNameField(name: String) {
        this.name = name
        subTaskName?.invoke(name)
    }

    internal fun setColor(color: COLOR) {
        this.color = color
        subColor?.invoke(color)
    }

    internal fun setIcon(icon: ICON) {
        this.icon = icon
        subIcon?.invoke(icon)
    }

    internal fun getTaskName(): String = this.name
    internal fun getColor(): COLOR = this.color
    internal fun getIcon(): ICON = this.icon
}