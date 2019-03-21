package com.xrater.monkeyTest

import androidx.test.uiautomator.*
import kotlin.random.Random

class MonkeyGenerator(val device: UiDevice) {

    val random = Random(0)

    fun generateAction() : () -> Unit {
        val clickableElements = device.findObjects(
            By.pkg(SimpleUiTest.APPLICATION_PACKAGE).clickable(true)
        )
        if (clickableElements.isEmpty()) {
            return {}
        }
        val clickable = clickableElements[random.nextInt(clickableElements.size)]
        try {
            if (clickable.resourceName != null) {
                if (clickable.resourceName.endsWith("/recent_apps") || clickable.resourceName.endsWith("/home")) {
                    return {}
                }
            }
            return generateActionWithElement(clickable)
        } catch (e: StaleObjectException) {}
        return {}
    }

    private fun generateActionWithElement(element: UiObject2): () -> Unit {
        if (random.nextBoolean()) {
            return { element.click() }
        } else {
            return {
                val initialText = element.text
                val text = generateText()
                element.text = text
                if (element.text == initialText) {
                    element.click()
                }
            }
        }
    }

    private fun generateText(): String {
        // TODO generate text
        return "SomeText"
    }

}
