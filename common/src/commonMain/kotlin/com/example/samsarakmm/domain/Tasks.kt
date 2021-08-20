package com.example.samsarakmm.domain

import java.io.Serializable

/**
 * Collections of tasks to make reads/writes easier
 *
 * See JavaDoc comments in "Day.java" for an explanation of why I am using
 * Serializable instead of Parcelable.
 */
class Tasks(val tasks: Array<Task>) : Serializable {
    fun get(): Array<Task> {
        return tasks
    }

   inline fun getFirstHalf(): List<Task> = tasks.asList().chunked(5)[0]
   inline fun getSecondHalf(): List<Task> = tasks.asList().chunked(5)[1]

    fun getTaskById(taskId: Int): Task? {
        for (task in tasks) {
            if (task.taskId == taskId) return task
        }
        return null
    }
}