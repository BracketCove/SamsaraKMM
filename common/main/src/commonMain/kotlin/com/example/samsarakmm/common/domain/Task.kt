package com.example.samsarakmm.common.domain

import com.example.samsarakmm.common.domain.constants.COLOR
import com.example.samsarakmm.common.domain.constants.ICON
import java.io.Serializable

/**
 * Domain Model is a pure language class
 * Data Model as being basically the same thing, but we can include libraries
 */
class Task(
    val taskId: Int, val taskName: String, //these things need to be translated into Resource Ids
    val taskIcon: ICON, val taskColor: COLOR
) : Serializable