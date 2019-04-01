package com.xrater.monkeyTest.model

import com.xrater.monkeyTest.net.NetState
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

class State(private val netState: NetState) {

    override fun equals(other: Any?): Boolean {
        return if (other is State) {
            EqualsBuilder()
                .append(netState, other.netState)
                .isEquals
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return HashCodeBuilder()
            .append(netState)
            .toHashCode()
    }

}