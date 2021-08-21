package com.example.samsarakmm.android.ui.managehourview

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.samsarakmm.android.SamsaraApp
import com.example.samsarakmm.android.common.showToast
import com.example.samsarakmm.android.ui.SamsaraTheme
import com.example.samsarakmm.android.ui.dayview.DayActivity
import com.example.samsarakmm.android.ui.managehourview.buildlogic.buildLogic
import com.example.samsarakmm.common.BaseViewLogic
import com.example.samsarakmm.domain.constants.Extras
import com.example.samsarakmm.domain.constants.Messages.GENERIC_ERROR_MESSAGE

class HourActivity : AppCompatActivity(), IHourContract.Container {
    lateinit var logic: BaseViewLogic<HourViewEvent>

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
        val hourInteger = i!!.getIntExtra(
            Extras.EXTRA_HOUR_INTEGER,
            0
        )

        val vm = HourViewModel(hourInteger)

        setContent {
            SamsaraTheme {
                HourScreen(
                    viewModel = vm,
                    eventHandler = logic::onViewEvent
                )
            }
        }

        logic = buildLogic(
            vm,
            (application as SamsaraApp).serviceLocator
        )
    }

    override fun onStart() {
        super.onStart()
        logic.onViewEvent(HourViewEvent.OnStart)
    }

    override fun navigateToDayView() {
        startActivity(
            Intent(
                this,
                DayActivity::class.java
            )
        )
    }

    override fun showMessage(message: String) = showToast(message)

}