package com.example.samsarakmm.common

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class DispatcherProvider : IDispatcherProvider {
    override fun provideUIContext(): CoroutineContext = Dispatchers.Main

    override fun provideIOContext(): CoroutineContext = Dispatchers.Default
}