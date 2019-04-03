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
            val positionJson = JSONObject()
            positionJson.put("x", center.x)
            positionJson.put("y", center.y)
            json.put("position", positionJson)
            actions.add(NetAction("TAP", json))
        }
    }

}