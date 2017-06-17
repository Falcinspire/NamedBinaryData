package com.falcinspire.nbd

import java.io.DataInput

/**
 * @author Falcinspire
 * @version Jun/14/2017 (5:11 PM)
 */

fun DataInput.readBooleanArray() : BooleanArray {
    val size = this.readInt()
    val buffer = BooleanArray(size)
    repeat(size) {index -> buffer[index] = this.readBoolean()}
    return buffer
}

fun DataInput.readByteArray() : ByteArray {
    val size = this.readInt()
    val buffer = ByteArray(size)
    this.readFully(buffer)
    return buffer
}

fun DataInput.readShortArray() : ShortArray {
    val size = this.readInt()
    val buffer = ShortArray(size)
    repeat(size) {index -> buffer[index] = this.readShort()}
    return buffer
}

fun DataInput.readIntArray() : IntArray {
    val size = this.readInt()
    val buffer = IntArray(size)
    repeat(size) {index -> buffer[index] = this.readInt()}
    return buffer
}

fun DataInput.readLongArray() : LongArray {
    val size = this.readInt()
    val buffer = LongArray(size)
    repeat(size) {index -> buffer[index] = this.readLong()}
    return buffer
}

fun DataInput.readFloatArray() : FloatArray {
    val size = this.readInt()
    val buffer = FloatArray(size)
    repeat(size) {index -> buffer[index] = this.readFloat()}
    return buffer
}

fun DataInput.readDoubleArray() : DoubleArray {
    val size = this.readInt()
    val buffer = DoubleArray(size)
    repeat(size) {index -> buffer[index] = this.readDouble()}
    return buffer
}

fun DataInput.readStringArray() : Array<String> {
    val size = this.readInt()
    val buffer = Array<String>(size) {""}
    repeat(size) {index -> buffer[index] = this.readUTF()}
    return buffer
}