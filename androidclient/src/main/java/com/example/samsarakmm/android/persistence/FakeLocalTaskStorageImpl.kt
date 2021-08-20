package com.example.samsarakmm.android.persistence

import com.example.samsarakmm.domain.ITaskStorage
import com.example.samsarakmm.domain.Task
import com.example.samsarakmm.domain.Tasks

class FakeLocalTaskStorageImpl : ITaskStorage {
    override fun getTasks(onSuccess: (Tasks) -> Unit, onError: (Exception) -> Unit) {
        onSuccess(PreloadData.preloadedTasks)
    }

    override fun getTask(taskId: Int, onSuccess: (Task) -> Unit, onError: (Exception) -> Unit) {
        onSuccess(PreloadData.preloadedTasks.getTaskById(taskId)!!)
    }

    override fun updateTask(task: Task, onSuccess: (Unit) -> Unit, onError: (Exception) -> Unit) {
       onSuccess(Unit)
    }
}