package com.falcinspire.nbd

import java.io.DataInput

/**
 * @author Falcinspire
 * @version Jun/14/2017 (12:12 AM)
 */

val readFunctions = mapOf<Byte, (DataInput) -> Any>(
        binaryID(Compound::class) to {input -> input.readCompound()},
        binaryID(Array<Any>::class) to {input -> input.readArray()},
        binaryID(Boolean::class) to {input -> (input.readByte() == 1.toByte())},
        binaryID(Byte::class) to {input -> input.readByte()},
        binaryID(Short::class) to {input -> input.readShort()},
        binaryID(Int::class) to {input -> input.readInt()},
        binaryID(Long::class) to {input -> input.readLong()},
        binaryID(Float::class) to {input -> input.readFloat()},
        binaryID(Double::class) to {input -> input.readDouble()},
        binaryID(String::class) to {input -> input.readUTF()},
        binaryID(BooleanArray::class) to {input -> input.readBooleanArray()},
        binaryID(ByteArray::class) to {input -> input.readByteArray()},
        binaryID(ShortArray::class) to {input -> input.readShortArray()},
        binaryID(IntArray::class) to { input -> input.readIntArray()},
        binaryID(LongArray::class) to { input -> input.readLongArray()},
        binaryID(FloatArray::class) to { input -> input.readFloatArray()},
        binaryID(DoubleArray::class) to {input -> input.readDoubleArray()},
        binaryID(Array<String>::class) to {input -> input.readStringArray()}
)

fun DataInput.readArray() : Array<Any> {
    val size = this.readInt()
    val type = this.readByte()
    val readFunc = readFunctions[type]

    readFunc ?: throw IllegalArgumentException("${type} is not a known type!")

    val array = Array<Any>(size) {
        readFunc(this)
    }

    return array
}

fun DataInput.readCompound() : Compound {
    val size = this.readInt()

    println("<< (${size})")

    val compound = Compound(size)

    repeat (size) { x ->
        val key = this.readUTF()
        val type = this.readByte()
        val readFunc = readFunctions[type]

        readFunc ?: throw IllegalArgumentException("${type} is not a known type!")

        val value = readFunc(this)
        println("<< ${key}: ${value}")

        compound.put(key, value)
    }

    return compound
}