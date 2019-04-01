package com.xrater.monkeyTest.net

import com.xrater.monkeyTest.model.Model
import java.lang.RuntimeException
import java.util.*


class NetMonkey {

    private val model = Model()
    private val stateProcessor = StateProcessor()

    private val random = Random()

    fun generateAction(netState: NetState): NetAction {
        val actions = stateProcessor.getActions(netState)
        return actions[random.nextInt(actions.size)]
//        val state = model.getState(netState)
//        if (state == null) {
//            val actions = stateProcessor.getActions(netState)
//            model.addState(netState, actions)
//            val newState = model.getState(netState)
//            if (newState != null) {
//                return model.generateAction(newState).netAction
//            } else {
//                throw RuntimeException("Failed to create state for some reasons")
//            }
//        }
//        return model.generateAction(state).netAction
    }

}