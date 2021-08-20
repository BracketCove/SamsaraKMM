package com.example.samsarakmm.android.persistence

import com.example.samsarakmm.domain.Day
import com.example.samsarakmm.domain.Hour
import com.example.samsarakmm.domain.IDayStorage
import com.example.samsarakmm.domain.constants.HOUR_MODE

class FakeLocalDayStorageImpl : IDayStorage {
    override fun getDay(onSuccess: (Day) -> Unit, onError: (Exception) -> Unit) {
        onSuccess.invoke(
            PreloadData.preloadedDay
        )
    }

    override fun updateDay(day: Day, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit) {
        onSuccess(Unit)
    }

    override fun getHourWithMode(
        hour: Int,
        onSuccess: (Hour, HOUR_MODE) -> Unit,
        onError: (Exception) -> Unit
    ) {
        val day = PreloadData.preloadedDay
        val hour = day.hours[hour]
        val mode = day.mode

        onSuccess(hour, mode)
    }

    override fun updateHour(hour: Hour, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit) {
        onSuccess(Unit)
    }
}