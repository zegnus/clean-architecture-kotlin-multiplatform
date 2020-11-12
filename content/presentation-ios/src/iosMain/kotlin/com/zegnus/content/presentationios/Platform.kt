package com.zegnus.content.presentationios

actual class Platform actual constructor() {
    actual val platform: String =
        platform.UIKit.UIDevice.currentDevice.systemName() + " " + platform.UIKit.UIDevice.currentDevice.systemVersion
}
