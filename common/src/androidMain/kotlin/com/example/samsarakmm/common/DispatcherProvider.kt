package com.example.samsarakmm.common

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual class DispatcherProvider {
    actual fun provideUIContext(): CoroutineContext = Dispatchers.Main
    actual fun provideIOContext(): CoroutineContext = Dispatchers.IO
}