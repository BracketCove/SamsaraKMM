package com.example.samsarakmm.common.database

import com.example.samsarakmm.common.IStorageService
import com.example.samsarakmm.common.domain.Day
import com.example.samsarakmm.common.domain.Hour
import com.example.samsarakmm.common.domain.Task
import com.example.samsarakmm.common.domain.Tasks
import com.example.samsarakmm.common.domain.constants.HOUR_MODE
import com.example.samsarakmm.storage.Database
import com.example.samsarakmm.storage.PreloadData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class StorageService(
    private val databaseDriverFactory: DatabaseDriverFactory,
) : IStorageService {
    private val database: Database = Database(databaseDriverFactory)

    override suspend fun getDay(onSuccess: (Day) -> Unit, onError: (Exception) -> Unit) {
        withContext(Dispatchers.Default) {
            try {
                var day = Day(
                    HOUR_MODE.TWENTY_FOUR_HOUR,
                    database.getHoursOfDay()
                )

                if (day.hours.isEmpty()) {
                    database.createHoursOfDay(
                        PreloadData.preloadedDay
                    )

                    day = PreloadData.preloadedDay
                }

                onSuccess(
                    day
                )

            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }

    override suspend fun updateDay(
        day: Day,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                onSuccess(
                    database.createHoursOfDay(day)
                )
            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }

    override suspend fun getHourWithMode(
        hour: Int,
        onSuccess: (Hour, HOUR_MODE) -> Unit,
        onError: (Exception) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                onSuccess(
                    database.getHourByInteger(hour),
                    HOUR_MODE.TWENTY_FOUR_HOUR
                )
            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }

    override suspend fun updateHour(
        hour: Hour,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                onSuccess(
                    database.updateHour(hour)
                )
            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }

    override suspend fun getTasks(onSuccess: (Tasks) -> Unit, onError: (Exception) -> Unit) {
        withContext(Dispatchers.Default) {
            try {

                var tasks = Tasks(
                    database.getAllTasks()
                )

                if (tasks.tasks.isEmpty()) {
                    database.createTasks(
                        PreloadData.preloadedTasks
                    )
                    
                    tasks = PreloadData.preloadedTasks
                }

                onSuccess(
                    tasks
                )
            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }

    override suspend fun getTask(
        taskId: Int,
        onSuccess: (Task) -> Unit,
        onError: (Exception) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                onSuccess(
                    database.getTaskById(taskId)
                )
            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }

    override suspend fun updateTask(
        task: Task,
        onSuccess: (Unit) -> Unit,
        onError: (Exception) -> Unit
    ) {
        withContext(Dispatchers.Default) {
            try {
                onSuccess(
                    database.updateTask(task)
                )
            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }


}