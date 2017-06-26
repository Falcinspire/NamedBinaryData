package com.falcinspire.nbd

import java.io.DataInput

/**
 * @author Falcinspire
 * @version Jun/14/2017 (12:12 AM)
 */

val readFunctions = mapOf<Byte, (DataInput) -> Any>(
        BinaryTypes.COMPOUND.id to {input -> input.readCompound()},
        BinaryTypes.LIST.id to {input -> input.readArray()},
        BinaryTypes.BOOLEAN.id to {input -> (input.readByte() == 1.toByte())},
        BinaryTypes.BYTE.id to {input -> input.readByte()},
        BinaryTypes.SHORT.id to {input -> input.readShort()},
        BinaryTypes.INT.id to {input -> input.readInt()},
        BinaryTypes.LONG.id to {input -> input.readLong()},
        BinaryTypes.FLOAT.id to {input -> input.readFloat()},
        BinaryTypes.DOUBLE.id to {input -> input.readDouble()},
        BinaryTypes.STRING.id to {input -> input.readUTF()},
        BinaryTypes.ARRAY_BOOLEAN.id to {input -> input.readBooleanArray()},
        BinaryTypes.ARRAY_BYTE.id to {input -> input.readByteArray()},
        BinaryTypes.ARRAY_SHORT.id to {input -> input.readShortArray()},
        BinaryTypes.ARRAY_INT.id to { input -> input.readIntArray()},
        BinaryTypes.ARRAY_LONG.id to { input -> input.readLongArray()},
        BinaryTypes.ARRAY_FLOAT.id to { input -> input.readFloatArray()},
        BinaryTypes.ARRAY_DOUBLE.id to {input -> input.readDoubleArray()},
        BinaryTypes.ARRAY_STRING.id to {input -> input.readStringArray()}
)

fun DataInput.readArray() : List<Any> {
    val size = this.readInt()
    val type = this.readByte()
    val readFunc = readFunctions[type]

    readFunc ?: throw IllegalArgumentException("${type} is not a known type!")

    val array = List<Any>(size) {
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