package com.zegnus.content.data

import com.zegnus.content.domain.ContentRepository
import com.zegnus.content.domain.Text

class ContentData: ContentRepository {

    override fun loadTexts(): List<Text> = listOf(
        Text("one"),
        Text("two"),
        Text("three"),
        Text("four")
    )
}
