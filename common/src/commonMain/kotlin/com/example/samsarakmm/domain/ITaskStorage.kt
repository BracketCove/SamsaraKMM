package com.example.samsarakmm.domain


interface ITaskStorage {
    fun getTasks(onSuccess: (Tasks) -> Unit, onError: (Exception) -> Unit)
    fun getTask(taskId: Int, onSuccess: (Task) -> Unit, onError: (Exception) -> Unit)
    fun updateTask(task: Task, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit)
}