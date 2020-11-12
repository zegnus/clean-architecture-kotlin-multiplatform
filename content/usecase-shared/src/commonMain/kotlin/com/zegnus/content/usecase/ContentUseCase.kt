package com.zegnus.content.usecase

import com.zegnus.content.domain.ContentDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// presentation (android) -> [use case (native) -> domain (native) <- data (native)] <- framework (android)
// UI (views, etc..) -> domain (kotlin native) <- framework (http client, database, filesystem)

@ExperimentalCoroutinesApi
class ContentUseCase(
    private val contentDomain: ContentDomain,
    private val coroutineScope: CoroutineScope
) {

    private val actionsFlow = MutableSharedFlow<Action>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    private val _useCaseFlow = MutableSharedFlow<ModelState>()
    val useCaseFlow = _useCaseFlow.asSharedFlow()

    fun start() {
        actionsFlow.asSharedFlow().onEach { action ->
            when (action) {
                Action.Refresh -> refresh()
                Action.Load -> Unit
            }
        }.launchIn(coroutineScope)
    }

    suspend fun action(action: Action) {
        actionsFlow.emit(action)
    }

    private suspend fun refresh() {
        _useCaseFlow.emit(ModelState.Loading)

        val map = contentDomain.loadTexts().map {
            it.text.toLowerCase()
        }

        delay(2000)

        _useCaseFlow.emit(ModelState.Loaded(map))
    }

    sealed class Action {
        object Refresh : Action()
        object Load : Action()
    }

    sealed class ModelState(val message: String) {
        object Loading : ModelState("Loading");
        data class Loaded(val texts: List<String>) : ModelState("Loading");
        object Error : ModelState("Error");
    }
}
