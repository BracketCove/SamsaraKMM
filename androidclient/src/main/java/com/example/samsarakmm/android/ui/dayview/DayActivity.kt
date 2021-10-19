package com.example.samsarakmm.android.ui.dayview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.samsarakmm.android.common.showToast
import com.example.samsarakmm.android.ui.dayview.buildlogic.buildLogic
import com.example.samsarakmm.android.ui.managehourview.HourActivity
import com.example.samsarakmm.android.ui.tasklistview.TaskListActivity
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.common.domain.constants.Extras
import com.example.samsarakmm.common.ui.SamsaraTheme
import com.example.samsarakmm.common.ui.dayview.DayViewScreen
import com.example.samsarakmm.ui.dayview.DayViewEvent
import com.example.samsarakmm.ui.dayview.DayViewModel
import com.example.samsarakmm.ui.dayview.IDayViewContract

//Feature level container
class DayActivity : AppCompatActivity(), IDayViewContract.Container {
    private lateinit var logic: BaseViewLogic<DayViewEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        View.OnClickListener {  }
        val viewModel = DayViewModel()

        setContent {
            SamsaraTheme {
                DayViewScreen(
                    eventHandler = logic::onViewEvent,
                    viewModel = viewModel
                )
            }
        }

        logic = buildLogic(
            viewModel
        )
    }

    override fun onStart() {
        super.onStart()
        logic.onViewEvent(DayViewEvent.OnStart)
    }

    override fun navigateToHourView(hourInteger: Int) {
        startActivity(
            Intent(
                this,
                HourActivity::class.java
            ).apply {
                putExtra(
                    Extras.EXTRA_HOUR_INTEGER,
                    hourInteger
                )
            }
        )
    }

    override fun navigateToTasksView() {
        startActivity(
            Intent(
                this,
                TaskListActivity::class.java
            )
        )
    }

    override fun showMessage(message: String) = showToast(message)

}