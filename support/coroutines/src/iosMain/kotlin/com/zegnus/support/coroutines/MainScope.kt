package com.zegnus.support.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class MainScope(private val mainContext: CoroutineContext) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler

    private val job = SupervisorJob()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showError(throwable)
    }

    private fun showError(t: Throwable) {
        t.printStackTrace()
        // log.e(throwable = t) { "Error in MainScope" }
    }

    fun onDestroy() {
        job.cancel()
    }
}
