package com.zegnus.support.coroutines

import kotlinx.coroutines.CoroutineDispatcher

expect class DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}
