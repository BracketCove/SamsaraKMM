package com.example.samsarakmm.storage

import com.example.samsarakmm.common.database.AppDatabase
import com.example.samsarakmm.common.database.DatabaseDriverFactory
import com.example.samsarakmm.common.domain.*
import com.example.samsarakmm.common.domain.constants.QUARTER
import com.example.samsarakmm.database.HourOfDay
import com.example.samsarakmm.database.UserTask

import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.EnumColumnAdapter

val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String) =
        if (databaseValue.isEmpty()) {
            listOf()
        } else {
            databaseValue.split(",")
        }

    override fun encode(value: List<String>) = value.joinToString(separator = ",")
}

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        UserTask.Adapter(
            EnumColumnAdapter(),
            EnumColumnAdapter()
        )
    )
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllTasks(): Array<Task> {
        return dbQuery.selectTasks().executeAsList().map { userTask: UserTask ->
            Task(
                userTask.taskId,
                userTask.taskName,
                userTask.icon,
                userTask.color
            )
        }.toTypedArray()
    }

    internal fun getTaskById(taskId: Int): Task {
        return dbQuery.selectTaskById(taskId).executeAsOne().let {
            Task(
                it.taskId,
                it.taskName,
                it.icon,
                it.color
            )
        }
    }


    internal fun getHoursOfDay(): Array<Hour> {
        return dbQuery.selectHoursOfDay().executeAsList().map { hour: HourOfDay ->
            hour.toHour()
        }.toTypedArray()
    }

    internal fun getHourByInteger(hourInteger: Int): Hour {
        dbQuery.selectHourByInteger(hourInteger).executeAsOne().let { hour ->
            return hour.toHour()
        }
    }

    private fun HourOfDay.toHour(): Hour = Hour(
        arrayOf(
            QuarterHour(
                firstQuarterTaskId,
                QUARTER.ZERO,
                firstQuarterIsActive
            ),
            QuarterHour(
                secondQuarterTaskId,
                QUARTER.FIFTEEN,
                secondQuarterIsActive
            ),
            QuarterHour(
                thirdQuarterTaskId,
                QUARTER.THIRTY,
                thirdQuarterIsActive
            ),
            QuarterHour(
                firstQuarterTaskId,
                QUARTER.FOURTY_FIVE,
                fourthQuarterIsActive
            )
        ),
        hourInteger
    )

    internal fun updateTask(task: Task) {
        dbQuery.updateTask(
            task.taskName,
            task.taskIcon,
            task.taskColor,
            task.taskId
        )
    }

    internal fun updateHour(hour: Hour) {
        dbQuery.updateHour(
            hour.quarters[0].taskId,
            hour.quarters[0].isActive,
            hour.quarters[1].taskId,
            hour.quarters[1].isActive,
            hour.quarters[2].taskId,
            hour.quarters[2].isActive,
            hour.quarters[3].taskId,
            hour.quarters[3].isActive,
            hour.hourInteger
        )
    }

    internal fun createHoursOfDay(day: Day) {
        dbQuery.transaction {
            day.hours.forEach { hour ->
                insertHourOfDay(hour)
            }
        }
    }

    internal fun createTasks(tasks: Tasks) {
        dbQuery.transaction {
            tasks.tasks.forEach { task ->
                insertTask(task)
            }
        }
    }

    internal fun insertHourOfDay(hour: Hour) {
        dbQuery.insertHour(
            hour.hourInteger,
            hour.quarters[0].taskId,
            hour.quarters[0].isActive,
            hour.quarters[1].taskId,
            hour.quarters[1].isActive,
            hour.quarters[2].taskId,
            hour.quarters[2].isActive,
            hour.quarters[3].taskId,
            hour.quarters[3].isActive,
        )
    }

    internal fun insertTask(task: Task) {
        dbQuery.insertTask(
            task.taskId,
            task.taskName,
            task.taskIcon,
            task.taskColor
        )
    }
}