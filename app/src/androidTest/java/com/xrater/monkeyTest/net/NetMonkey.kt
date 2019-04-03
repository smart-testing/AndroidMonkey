package com.xrater.monkeyTest.net

import com.xrater.monkeyTest.model.Model


class NetMonkey {

    private val model = Model()
    private val stateProcessor = StateProcessor()

    fun generateAction(netState: NetState): NetAction {
        val state = model.getState(netState)
        if (state == null) {
            val actions = stateProcessor.getActions(netState)
            model.addState(netState, actions)
            val newState = model.getState(netState)
            if (newState != null) {
                return model.generateAction(newState).netAction
            } else {
                throw RuntimeException("Failed to create state for some reasons")
            }
        }
        return model.generateAction(state).netAction
    }

}