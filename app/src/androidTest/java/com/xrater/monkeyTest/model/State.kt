package com.xrater.monkeyTest.model

import com.xrater.monkeyTest.net.NetAction
import java.lang.Exception
import java.util.stream.Collectors

class State(actions: List<NetAction>) {

    val actions: MutableList<Action> = actions.stream()
        .map { action -> Action(action) }
        .peek { action -> action.from = this }
        .collect(Collectors.toList())

    var interest = actions.size

    fun link(action: Action, to: State?) {
        if (!actions.contains(action) || action.from != this) {
            throw Exception("No such action for state")
        }
        action.to = if (action.to != null && action.to != to) null else to
        interest = evaluateInterest()
    }

    fun generateAction(): Action {
        return actions.stream()
            .max { a1, a2 ->
                run {
                    val firstStateSnapshot = a1.to
                    val secondStateSnapshot = a2.to
                    if (firstStateSnapshot == null) {
                        1
                    } else {
                        if (secondStateSnapshot == null) {
                            -1
                        } else {
                            firstStateSnapshot.interest - secondStateSnapshot.interest
                        }
                    }
                }
            }.orElseThrow { Exception() }
    }

    private fun evaluateInterest(): Int {
        var newInterest = 0
        actions.stream()
            .forEach { action ->
                run {
                    if (action.to == null) {
                        newInterest += 1
                    }
                }
            }
        return newInterest
    }

}