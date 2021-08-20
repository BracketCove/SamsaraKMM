package com.example.samsarakmm.android.common

import kotlinx.coroutines.Job

abstract class BaseLogic<EVENT>  {
    protected lateinit var jobTracker: Job
    abstract fun onEvent(event: EVENT)
}