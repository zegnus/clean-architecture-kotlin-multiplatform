package com.zegnus.content.presentationios

import com.zegnus.content.data.ContentData
import com.zegnus.content.domain.ContentDomain
import com.zegnus.content.usecase.ContentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ContentPresenter {
    private val scope: CoroutineScope = CustomMainScope()
    private val contentUseCase: ContentUseCase = ContentUseCase(ContentDomain(ContentData()), scope)

    private val _sharedFlow = MutableSharedFlow<String>()

    fun start(callback: (String) -> Unit) {
        runBlocking {
            callback("call from blocking")
            withContext(Dispatchers.Default) {
                callback("call from with context")
            }

            _sharedFlow.asSharedFlow()
                .onEach { text -> callback(text) }
                .launchIn(this)
        }
    }

    fun action(action: ContentUseCase.Action) = runBlocking {
        _sharedFlow.emit("value from flow")
    }
}

