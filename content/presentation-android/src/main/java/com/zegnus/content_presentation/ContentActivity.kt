package com.zegnus.content_presentation

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.zegnus.content.usecase.ContentUseCase
import com.zegnus.content.usecase.ContentUseCaseDependencies
import com.zegnus.content_presentation.ContentDisplayer.ContentDisplayerAction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ContentActivity : AppCompatActivity() {

    private lateinit var presenter: ContentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val contentDisplayerAction: (ContentDisplayerAction) -> Unit = { action ->
            when (action) {
                ContentDisplayerAction.Refresh -> {
                    lifecycleScope.launch { presenter.action(ContentUseCase.Action.Refresh) }
                }
            }
        }

        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ContentPresenter(
                    contentDisplayer = ContentDisplayer(
                        textView = findViewById(R.id.textView),
                        refreshButton = findViewById(R.id.buttonView),
                        actions = contentDisplayerAction
                    ),
                    contentUseCase = applicationContext
                        .contentUseCaseDependencies()
                        .contentUseCase(lifecycleScope),
                    coroutineScope = lifecycleScope
                ) as T
            }
        }

        presenter = ViewModelProvider(this, factory).get(ContentPresenter::class.java)

        findViewById<Button>(R.id.buttonView).setOnClickListener {
            lifecycleScope.launch {
                presenter.action(ContentUseCase.Action.Refresh)
            }
        }

        presenter.startPresenting()
    }
}

@ExperimentalCoroutinesApi
fun Context.contentUseCaseDependencies(): ContentUseCaseDependencies =
    applicationContext as ContentUseCaseDependencies
