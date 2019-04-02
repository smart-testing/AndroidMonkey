package com.xrater.monkeyTest.system

import androidx.test.uiautomator.*
import com.xrater.monkeyTest.net.NetElement

class SystemElement(val obj: UiObject2) {

    fun buildNetElement() : NetElement {
        return NetElement(this)
    }

}