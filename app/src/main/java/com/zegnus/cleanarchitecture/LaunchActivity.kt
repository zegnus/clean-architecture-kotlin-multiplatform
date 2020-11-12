package com.zegnus.cleanarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zegnus.navigation.Navigation

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Navigation.toContent(this)
    }
}
