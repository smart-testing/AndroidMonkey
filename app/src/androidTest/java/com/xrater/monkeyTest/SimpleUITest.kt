package com.xrater.monkeyTest

import android.content.Context
import android.content.Intent

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.uiautomator.*

import org.junit.Before
import org.junit.Test

class SimpleUiTest {

    private val device = UiDevice.getInstance(getInstrumentation())
    private val context = getApplicationContext<Context>()

    @Before
    fun launchApp() {
        openApplication()
    }

    @Test
    fun actionsTest() {
        val monkeyGenerator = MonkeyGenerator(device)
        for (step in 0..STEPS_NUMBER) {
            openApplicationIfRequired()
            val action = monkeyGenerator.generateAction()
            try {
                action()
            } catch (e: StaleObjectException) { monkeyGenerator.clear() }
        }
    }

    private fun openApplicationIfRequired() {
        if (device.currentPackageName != APPLICATION_PACKAGE) {
            openApplication()
        }
    }

    private fun openApplication() {
        device.pressHome()
        val intent = context.packageManager.getLaunchIntentForPackage(APPLICATION_PACKAGE)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(APPLICATION_PACKAGE).depth(0)), LONG_WAIT)
    }

    companion object {
        // TODO add package name
        const val APPLICATION_PACKAGE = "com.avjindersinghsekhon.minimaltodo"
        const val STEPS_NUMBER = 50
        const val LONG_WAIT = 5000L
    }
}