package com.xrater.monkeyTest

import androidx.test.uiautomator.*
import kotlin.random.Random

class MonkeyGenerator(val device: UiDevice) {

    private val random = Random(0)

    fun generateAction() : () -> Unit {
        try {
            val elements =
                device.findObjects(By.pkg(SimpleUiTest.APPLICATION_PACKAGE).clickable(true))
                    .map { UIElement(it) }
            if (elements.isEmpty()) {
                return {}
            }
            elements.filter {
                it.uiObject.resourceName == null || !(it.uiObject.resourceName.endsWith("/recent_apps") && it.uiObject.resourceName.endsWith("/home"))
            }

            return {
                if (random.nextInt(4) == 0) {
                    fillForm(elements)
                }
                val element = findButton(elements)
                System.out.println(element.uiObject.resourceName)
                element.uiObject.click()
            }
        } catch (e: StaleObjectException) { clear() }
        return { }
    }

    private fun findButton(elements: List<UIElement>): UIElement {
        return elements.filter { element -> !element.isInput }.shuffled().first()
    }

    fun clear() {}

    private fun fillForm(elements: List<UIElement>) {
        for (element in elements) {
            if (setText(element.uiObject, generateText())) {
                element.isInput = true
            }
        }
    }

    private fun setText(element: UiObject2, text: String): Boolean {
        val initialText = element.text
        element.text = text
        return element.text != initialText
    }

    private fun generateActionWithElement(element: UiObject2): () -> Unit {
        if (random.nextBoolean()) {
            return { element.click() }
        } else {
            return {
                if (!setText(element, generateText())) {
                    element.click()
                }
            }
        }
    }

    private fun generateText(): String {
        val formats = listOf("%s@gmail.com", "%s", "%s", "%s")
        val format = formats[random.nextInt(formats.size)]
        return format.format("text")
    }

    private class UIElement(obj: UiObject2) {

        val uiObject: UiObject2 = obj
        var isInput: Boolean = false
    }
}
