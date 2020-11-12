package com.zegnus.content_presentation

import androidx.lifecycle.ViewModel
import com.zegnus.content.Platform
import com.zegnus.content.usecase.ContentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*

@ExperimentalCoroutinesApi
class ContentPresenter(
    private val contentDisplayer: ContentDisplayer,
    private val contentUseCase: ContentUseCase,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    fun startPresenting() {
        contentUseCase.start()

        contentUseCase.useCaseFlow.onEach { modelState ->
            contentDisplayer.display(modelState.toViewState())
        }.launchIn(coroutineScope)
    }

    suspend fun action(action: ContentUseCase.Action) {
        when (action) {
            ContentUseCase.Action.Refresh -> contentUseCase.action(ContentUseCase.Action.Refresh)
        }
    }

    data class ViewState(val text: String)
}

@ExperimentalCoroutinesApi
private fun ContentUseCase.ModelState.toViewState() =
    when (this) {
        is ContentUseCase.ModelState.Loading -> ContentPresenter.ViewState(message + " from ${Platform().platform}")
        is ContentUseCase.ModelState.Loaded -> ContentPresenter.ViewState(texts[Random().nextInt(4)])
        is ContentUseCase.ModelState.Error -> ContentPresenter.ViewState(message)
    }
