package com.example.samsarakmm.common

open class BaseViewModel() {
    var isLoading = true
        set(value) {
            field = value
            subIsLoading?.invoke(value)
        }

    var subIsLoading: ((Boolean) -> Unit)? = null
}