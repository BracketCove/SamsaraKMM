package com.example.samsarakmm.ui.managehourview


interface IHourContract {
    interface Container {
        fun navigateToDayView()
        fun showMessage(message: String)
    }
}