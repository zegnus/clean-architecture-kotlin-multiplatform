package com.zegnus.content.presentationios

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
