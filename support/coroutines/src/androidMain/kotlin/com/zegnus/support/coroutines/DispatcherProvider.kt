package com.zegnus.support.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class DispatcherProvider {

    actual val main: CoroutineDispatcher = Dispatchers.Main

    actual val io: CoroutineDispatcher = Dispatchers.IO

    actual val default: CoroutineDispatcher = Dispatchers.Default
}
