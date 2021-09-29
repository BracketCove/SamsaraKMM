package com.example.samsarakmm.android.ui.tasklistview

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.samsarakmm.android.SamsaraApp
import com.example.samsarakmm.android.common.showToast
import com.example.samsarakmm.android.ui.SamsaraTheme
import com.example.samsarakmm.android.ui.dayview.DayActivity
import com.example.samsarakmm.android.ui.managetaskview.TaskActivity
import com.example.samsarakmm.android.ui.tasklistview.buildlogic.buildLogic
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.domain.constants.Extras
import com.example.samsarakmm.ui.tasklistview.ITaskListViewContract
import com.example.samsarakmm.ui.tasklistview.TaskListViewEvent
import com.example.samsarakmm.ui.tasklistview.TaskListViewModel

class TaskListActivity : AppCompatActivity(), ITaskListViewContract.Container {

    private lateinit var logic: BaseViewLogic<TaskListViewEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = TaskListViewModel()

        setContent {
            SamsaraTheme {
                TaskListScreen(
                    viewModel,
                    logic::onViewEvent
                )
            }
        }

        logic = buildLogic(
            viewModel,
            (application as SamsaraApp).serviceLocator
        )
    }

    override fun onStart() {
        super.onStart()
        logic.onViewEvent(TaskListViewEvent.OnStart)
    }

    override fun showMessage(message: String) = showToast(message)



    override fun navigateToDayView() {
        startActivity(
            Intent(
                this,
                DayActivity::class.java
            )
        )
    }

    override fun navigateToTaskView(taskId: Int) {
        startActivity(
            Intent(
                this,
                TaskActivity::class.java
            ).apply {
                putExtra(
                    Extras.EXTRA_TASK_ID,
                    taskId
                )
            }
        )
    }
}