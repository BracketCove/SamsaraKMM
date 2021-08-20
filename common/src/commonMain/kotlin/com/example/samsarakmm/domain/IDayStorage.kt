package com.example.samsarakmm.domain

import com.example.samsarakmm.domain.Day
import com.example.samsarakmm.domain.Hour
import com.example.samsarakmm.domain.constants.HOUR_MODE

/**
 * The facade pattern uses an abstraction (interface or abstract class) to hide the implementation
 * details of a sub-system, from the client (class) that use the sub-system
 *
 * We hide the implementation details of the back end, from the front end (client)
 */
interface IDayStorage {
    fun getDay(onSuccess: (Day) -> Unit, onError: (Exception) -> Unit)
    fun updateDay(day: Day, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit )
    fun getHourWithMode(hour: Int, onSuccess: (Hour, HOUR_MODE) -> Unit, onError: (Exception) -> Unit)
    fun updateHour(hour: Hour, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit)
}