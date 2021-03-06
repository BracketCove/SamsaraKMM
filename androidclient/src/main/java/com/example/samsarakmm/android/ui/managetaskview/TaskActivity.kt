package com.example.samsarakmm.android.ui.managetaskview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.samsarakmm.android.common.showToast
import com.example.samsarakmm.android.ui.dayview.DayActivity
import com.example.samsarakmm.android.ui.managetaskview.buildlogic.buildLogic
import com.example.samsarakmm.android.ui.tasklistview.TaskListActivity
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.domain.constants.Extras
import com.example.samsarakmm.common.domain.constants.Messages.GENERIC_ERROR_MESSAGE
import com.example.samsarakmm.common.ui.SamsaraTheme
import com.example.samsarakmm.common.ui.managetaskview.TaskScreen
import com.example.samsarakmm.ui.managetaskview.ITaskViewContract
import com.example.samsarakmm.ui.managetaskview.TaskViewEvent
import com.example.samsarakmm.ui.managetaskview.TaskViewModel

class TaskActivity : AppCompatActivity(), ITaskViewContract.Container {
    private lateinit var logic: BaseViewLogic<TaskViewEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val i = this.intent
        if (i == null) {
            Toast.makeText(
                this,
                GENERIC_ERROR_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
            startActivity(
                Intent(this, DayActivity::class.java)
            )
        }

        val taskId = i!!.getIntExtra(
            Extras.EXTRA_TASK_ID,
            0
        )

        val viewModel = TaskViewModel(taskId)

        setContent {
            SamsaraTheme {
                TaskScreen(
                    viewModel = viewModel,
                    eventHandler = logic::onViewEvent
                )
            }
        }

        logic = buildLogic(
            viewModel
        )
    }

    override fun onStart() {
        super.onStart()
        logic.onViewEvent(TaskViewEvent.OnStart)
    }

    override fun goToTaskListActivity() = startActivity(
        Intent(
            this,
            TaskListActivity::class.java
        )
    )

    override fun showMessage(message: String) = showToast(message)
}