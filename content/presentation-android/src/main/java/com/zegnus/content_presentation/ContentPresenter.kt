package com.zegnus.content_presentation

import androidx.lifecycle.ViewModel
import com.zegnus.content.usecase.ContentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class ContentPresenter(
    private val contentDisplayer: ContentDisplayer,
    private val contentUseCase: ContentUseCase,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    fun startPresenting() {
        contentUseCase.start()

        contentUseCase.useCaseFlow.onEach { modelState ->
            contentDisplayer.display(ViewState(modelState.message))
        }.launchIn(coroutineScope)
    }

    suspend fun action(action: ContentUseCase.Action) {
        when (action) {
            ContentUseCase.Action.Refresh -> contentUseCase.action(ContentUseCase.Action.Refresh)
        }
    }

    data class ViewState(val text: String)
}
