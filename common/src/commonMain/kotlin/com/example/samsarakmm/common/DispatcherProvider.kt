package com.example.samsarakmm.common

import kotlin.coroutines.CoroutineContext

expect class DispatcherProvider {
    fun provideUIContext(): CoroutineContext

    fun provideIOContext(): CoroutineContext
}