package com.falcinspire.nbd

import java.io.DataOutput

/**
 * @author Falcinspire
 * @version Jun/14/2017 (5:11 PM)
 */

fun DataOutput.writeBooleanArray(array: BooleanArray) {
    this.writeInt(array.size)
    repeat(array.size) {index -> this.writeBoolean(array[index])}
}

fun DataOutput.writeByteArray(array: ByteArray) {
    this.writeInt(array.size)
    this.write(array)
}

fun DataOutput.writeShortArray(array: ShortArray) {
    this.writeInt(array.size)
    repeat(array.size) {index -> this.writeShort(array[index].toInt())}

}

fun DataOutput.writeIntArray(array: IntArray) {
    this.writeInt(array.size)
    repeat(array.size) {index -> this.writeInt(array[index])}

}

fun DataOutput.writeLongArray(array: LongArray) {
    this.writeInt(array.size)
    repeat(array.size) {index -> this.writeLong(array[index])}

}

fun DataOutput.writeFloatArray(array: FloatArray) {
    this.writeInt(array.size)
    repeat(array.size) {index -> this.writeFloat(array[index])}

}

fun DataOutput.writeDoubleArray(array: DoubleArray) {
    this.writeInt(array.size)
    repeat(array.size) {index -> this.writeDouble(array[index])}

}

fun DataOutput.writeStringArray(array: Array<String>) {
    this.writeInt(array.size)
    repeat(array.size) {index -> this.writeUTF(array[index])}

}