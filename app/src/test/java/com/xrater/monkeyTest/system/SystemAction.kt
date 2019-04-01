package com.xrater.monkeyTest.system

import androidx.test.uiautomator.*
import com.xrater.monkeyTest.net.NetAction

class SystemAction(action: NetAction, private val device: UiDevice) {

    private val activity : () -> Unit = {}

    fun perform() {
        activity()
    }

}