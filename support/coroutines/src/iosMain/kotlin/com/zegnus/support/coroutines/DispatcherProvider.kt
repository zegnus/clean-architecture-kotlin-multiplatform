package com.zegnus.support.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
actual class DispatcherProvider {

    actual val main: CoroutineDispatcher = Dispatchers.Main

    actual val io: CoroutineDispatcher
//        get() = newSingleThreadContext("io-thread")
        get() = Dispatchers.Main

    actual val default: CoroutineDispatcher
//        get() = newSingleThreadContext("default-thread")
        get() = Dispatchers.Main
}
