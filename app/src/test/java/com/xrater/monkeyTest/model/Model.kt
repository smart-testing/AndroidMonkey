package com.xrater.monkeyTest.model

import com.xrater.monkeyTest.net.NetAction
import com.xrater.monkeyTest.net.NetState


class Model {

    fun getState(state: NetState) : State {
        TODO()
    }

    fun generateAction(state: State) : Action {
        TODO()
    }

    fun addState(state: NetState, actions: List<NetAction>) {
        TODO()
    }

    private fun linkStates(fromState: State, tState: State, action: Action) {
        TODO()
    }

}