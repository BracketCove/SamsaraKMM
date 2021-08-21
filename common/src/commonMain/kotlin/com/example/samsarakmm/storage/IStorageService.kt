package com.example.samsarakmm.storage

import com.example.samsarakmm.domain.Day
import com.example.samsarakmm.domain.Hour
import com.example.samsarakmm.domain.Task
import com.example.samsarakmm.domain.Tasks
import com.example.samsarakmm.domain.constants.HOUR_MODE

interface IStorageService {
    suspend fun getDay(onSuccess: (Day) -> Unit, onError: (Exception) -> Unit)
    suspend fun updateDay(day: Day, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit)
    suspend fun getHourWithMode(
        hour: Int,
        onSuccess: (Hour, HOUR_MODE) -> Unit,
        onError: (Exception) -> Unit
    )
    suspend fun updateHour(hour: Hour, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit)

    suspend fun getTasks(onSuccess: (Tasks) -> Unit, onError: (Exception) -> Unit)
    suspend fun getTask(taskId: Int, onSuccess: (Task) -> Unit, onError: (Exception) -> Unit)
    suspend fun updateTask(task: Task, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit)
}