package com.example.samsarakmm.android.ui.managehourview


interface IHourContract {
    interface Container {
        fun navigateToDayView()
        fun showMessage(message: String)
    }
}