package com.falcinspire.nbd

import kotlin.reflect.KType

/**
 * @author Falcinspire
 * @version Jun/16/2017 (8:16 PM)
 */

fun <T : KType> T.kClass() = this.javaClass.kotlin
    