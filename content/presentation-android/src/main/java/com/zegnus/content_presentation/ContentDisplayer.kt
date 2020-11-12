package com.zegnus.content_presentation

import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ContentDisplayer(
    private val textView: TextView,
    refreshButton: Button,
    actions: (ContentDisplayerAction) -> (Unit)
) {

    init {
        refreshButton.setOnClickListener { actions(ContentDisplayerAction.Refresh) }
    }

    fun display(viewState: ContentPresenter.ViewState) {
        textView.text = viewState.text
    }

    sealed class ContentDisplayerAction {
        object Refresh : ContentDisplayerAction()
    }
}
