package com.zegnus.content.domain

class ContentDomain(private val contentRepository: ContentRepository) {

    fun loadTexts(): List<Text> {
        val texts = contentRepository.loadTexts()
        return texts.reversed()
    }
}

data class Text(val text: String)

interface ContentRepository {

    fun loadTexts(): List<Text>
}
