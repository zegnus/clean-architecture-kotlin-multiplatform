package com.zegnus.cleanarchitecture.dependencies

import com.zegnus.content.data.ContentData
import com.zegnus.content.domain.ContentDomain
import com.zegnus.content.usecase.ContentUseCase
import com.zegnus.content.usecase.ContentUseCaseDependencies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ContentDependenciesGraph : ContentUseCaseDependencies {

    override fun contentUseCase(coroutineScope: CoroutineScope): ContentUseCase =
        ContentUseCase(
            contentDomain = ContentDomain(
                contentRepository = ContentData()
            ),
            coroutineScope = coroutineScope
        )
}
