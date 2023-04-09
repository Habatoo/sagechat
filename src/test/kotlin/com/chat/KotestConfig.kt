package com.chat

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

@Suppress("unused")
object KotestConfig : AbstractProjectConfig() {

    override val isolationMode: IsolationMode
        get() = IsolationMode.InstancePerLeaf
}
