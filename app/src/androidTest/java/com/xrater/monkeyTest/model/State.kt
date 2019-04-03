package com.xrater.monkeyTest.model

import com.xrater.monkeyTest.net.NetAction
import java.lang.Exception
import java.util.stream.Collectors

class State(actions: List<NetAction>) {

    private val actions: MutableList<Action> = actions.stream()
        .map { action -> Action(action) }
        .peek { action -> action.from = this }
        .collect(Collectors.toList())

    fun link(action: Action, to: State?) {
        if (!actions.contains(action) || action.from != this) {
            throw Exception("No such action for state")
        }
        action.to = if (action.to != null && action.to != to) null else to
    }

    fun generateAction(): Action {
        val unknown = actions.filter { action -> action.to == null }
        return if (unknown.isEmpty()) actions.random() else unknown.random()
    }

//    override fun equals(other: Any?): Boolean {
//        return if (other is State) {
//            EqualsBuilder()
//                .append(netState, other.netState)
//                .isEquals
//        } else {
//            false
//        }
//    }
//
//    override fun hashCode(): Int {
//        return HashCodeBuilder()
//            .append(netState)
//            .toHashCode()
//    }

}