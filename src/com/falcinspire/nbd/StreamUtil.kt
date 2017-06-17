package com.falcinspire.nbd

import java.io.InputStream
import java.io.OutputStream

/**
 * @author Falcinspire
 * @version Jun/15/2017 (8:24 PM)
 */

fun <T, S : InputStream> S.closeAfter(action: (S) -> T) : T{
    val result = action(this)
    this.close()
    return result
}

fun <S : OutputStream> S.closeAfter(action: (S) -> Unit) {
    action(this)
    this.flush()
    this.close()
}