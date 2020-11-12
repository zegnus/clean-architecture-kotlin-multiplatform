package com.zegnus.cleanarchitecture

import android.app.Application
import com.zegnus.cleanarchitecture.dependencies.ContentDependenciesGraph
import com.zegnus.content.usecase.ContentUseCase
import com.zegnus.content.usecase.ContentUseCaseDependencies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CleanArchitectureApplication : Application(), ContentUseCaseDependencies {

    lateinit var contentDependenciesGraph: ContentDependenciesGraph

    override fun onCreate() {
        super.onCreate()

        contentDependenciesGraph = ContentDependenciesGraph()
    }

    override fun contentUseCase(coroutineScope: CoroutineScope): ContentUseCase =
        contentDependenciesGraph.contentUseCase(coroutineScope)
}
