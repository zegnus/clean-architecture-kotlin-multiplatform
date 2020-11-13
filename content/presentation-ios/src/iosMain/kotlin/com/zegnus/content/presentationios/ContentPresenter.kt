package com.zegnus.content.presentationios

import com.zegnus.content.usecase.ContentUseCase
import com.zegnus.support.coroutines.DispatcherProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class ContentPresenter {
    private val scope: CoroutineScope = MainScope()
    private val dispatcherProvider = DispatcherProvider()

    private val _sharedFlow = MutableSharedFlow<String>()

    fun start(callback: (String) -> Unit) {
        scope.launch {
            callback("call from blocking")
            withContext(dispatcherProvider.default) {
                callback("call from with context")
            }

            _sharedFlow.asSharedFlow()
                .onEach { text -> callback(text) }
                .launchIn(this)
        }
    }

    fun action(action: ContentUseCase.Action) = scope.launch {
        _sharedFlow.emit("value from flow")
    }
}

