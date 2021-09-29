package com.example.samsarakmm.ui.managetaskview

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

    var subColor: ((COLOR) -> Unit)? = null
    var subIcon: ((ICON) -> Unit)? = null
    var subTaskName: ((String) -> Unit)? = null

    fun setTaskNameField(name: String) {
        this.name = name
        subTaskName?.invoke(name)
    }

    fun setColor(color: COLOR) {
        this.color = color
        subColor?.invoke(color)
    }

    fun setIcon(icon: ICON) {
        this.icon = icon
        subIcon?.invoke(icon)
    }

    fun getTaskName(): String = this.name
    fun getColor(): COLOR = this.color
    fun getIcon(): ICON = this.icon
}