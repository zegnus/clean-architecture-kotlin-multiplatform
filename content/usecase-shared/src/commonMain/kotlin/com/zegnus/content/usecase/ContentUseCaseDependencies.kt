package com.zegnus.content.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
interface ContentUseCaseDependencies {

    fun contentUseCase(coroutineScope: CoroutineScope): ContentUseCase
}
