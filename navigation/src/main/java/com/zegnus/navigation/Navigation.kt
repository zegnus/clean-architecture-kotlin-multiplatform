package com.zegnus.navigation

import android.content.Context
import android.content.Intent
import com.zegnus.content_presentation.ContentActivity

object Navigation {

    fun toContent(context: Context) {
        val intent = Intent(context, ContentActivity::class.java)
        context.startActivity(intent)
    }
}
