package com.example.samsarakmm.common

import kotlin.coroutines.CoroutineContext

interface IDispatcherProvider {
    fun provideUIContext() : CoroutineContext
    fun provideIOContext() : CoroutineContext
}