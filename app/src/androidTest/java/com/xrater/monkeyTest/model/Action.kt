package com.xrater.monkeyTest.model

import com.xrater.monkeyTest.net.NetAction

class Action(val netAction : NetAction) {

    var from: State? = null
    var to: State? = null

}