package com.xrater.monkeyTest.net

import org.json.JSONObject


class StateProcessor {

    fun getActions(state: NetState) : List<NetAction> {
        val actions = mutableListOf<NetAction>()
        state.elements.stream()
            .forEach  { element -> addActionsToList(actions, element) }
        return actions
    }

    private fun addActionsToList(actions: MutableList<NetAction>, element: NetElement) {
        if (element.isClickable) {
            val center = element.center
            val json = JSONObject()
            json.put("position", mapOf("x" to center.x, "y" to center.y))
            actions.add(NetAction("TAP", json))
        }
    }

}