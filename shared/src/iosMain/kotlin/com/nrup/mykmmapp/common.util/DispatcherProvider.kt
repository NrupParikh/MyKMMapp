package com.nrup.mykmmapp.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class IOSDispatcher : DispatcherProvider {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

}


internal actual fun provideDispatcher(): DispatcherProvider = IOSDispatcher()