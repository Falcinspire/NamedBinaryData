package com.falcinspire.nbd

import java.io.DataInput

/**
 * @author Falcinspire
 * @version Jun/14/2017 (12:12 AM)
 */

val readFunctions = mapOf<Byte, (DataInput) -> Object>(
        binaryID(HashMap::class) to {input -> input.readByte() as Object},
        binaryID(Array<Any>::class) to {input -> input.readByte() as Object},
        binaryID(Boolean::class) to {input -> (input.readByte() == 1.toByte()) as Object},
        binaryID(Byte::class) to {input -> input.readByte() as Object},
        binaryID(Short::class) to {input -> input.readShort() as Object},
        binaryID(Int::class) to {input -> input.readInt() as Object},
        binaryID(Float::class) to {input -> input.readLong() as Object},
        binaryID(Double::class) to {input -> input.readFloat() as Object},
        binaryID(String::class) to {input -> input.readDouble() as Object},
        binaryID(BooleanArray::class) to {input -> input.readByteArray() as Object},
        binaryID(ByteArray::class) to {input -> input.readByteArray() as Object},
        binaryID(ShortArray::class) to {input -> input.readByte() as Object},
        binaryID(IntArray::class) to { input -> input.readIntArray() as Object},
        binaryID(LongArray::class) to { input -> input.readByte() as Object},
        binaryID(FloatArray::class) to { input -> input.readByte() as Object},
        binaryID(DoubleArray::class) to {input -> input.readByte() as Object},
        binaryID(Array<String>::class) to {input -> input.readByte() as Object}
        )

fun DataInput.readByteArray() : ByteArray {
    val size = this.readInt()
    val buffer = ByteArray(size)
    this.readFully(buffer)
    return buffer
}

fun DataInput.readIntArray() : IntArray {
    val size = this.readInt()
    val buffer = IntArray(size)
    repeat(size) {index -> buffer[index] = this.readInt()}
    return buffer
}
    