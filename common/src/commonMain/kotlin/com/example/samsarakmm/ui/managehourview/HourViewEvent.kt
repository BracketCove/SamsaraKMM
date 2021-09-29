package com.example.samsarakmm.ui.managehourview

import com.example.samsarakmm.domain.constants.QUARTER

sealed class HourViewEvent {
    object OnStart : HourViewEvent()
    object OnDoneClick : HourViewEvent()
    data class OnQuarterToggled(val quarter: QUARTER, val active: Boolean) : HourViewEvent()
    data class OnTaskSelected(val quarter: QUARTER, val position: Int) : HourViewEvent()
}