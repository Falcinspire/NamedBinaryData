package com.falcinspire.nbd

import java.io.DataOutput
import kotlin.reflect.KClass

/**
 * @author Falcinspire
 * @version Jun/14/2017 (12:12 AM)
 */

val writeFunctions = mapOf<KClass<*>, (DataOutput, Any) -> Unit>(
        Compound::class to {output, value -> output.writeCompound((value as Compound))},
        Array<Any>::class to {output, value -> output.writeArray((value as Array<Any>))},
        Boolean::class to {output, value -> output.writeBoolean((value as Boolean))},
        Byte::class to {output, value -> output.writeByte((value as Byte).toInt())},
        Short::class to {output, value -> output.writeShort((value as Short).toInt())},
        Int::class to {output, value -> output.writeInt((value as Int))},
        Long::class to {output, value -> output.writeLong((value as Long))},
        Float::class to {output, value -> output.writeFloat((value as Float))},
        Double::class to {output, value -> output.writeDouble((value as Double))},
        String::class to {output, value -> output.writeUTF((value as String))},
        BooleanArray::class to {output, value -> output.writeBooleanArray((value as BooleanArray))},
        ByteArray::class to {output, value -> output.writeByteArray((value as ByteArray))},
        ShortArray::class to {output, value -> output.writeShortArray((value as ShortArray))},
        IntArray::class to { output, value -> output.writeIntArray((value as IntArray))},
        LongArray::class to { output, value -> output.writeLongArray((value as LongArray))},
        FloatArray::class to { output, value -> output.writeFloatArray((value as FloatArray))},
        DoubleArray::class to {output, value -> output.writeDoubleArray((value as DoubleArray))},
        Array<String>::class to {output, value -> output.writeStringArray((value as Array<String>))}
)

fun DataOutput.writeArray(array : Array<Any>) {

    if (array.size == 0) return

    this.writeInt(array.size)
    this.writeByte(binaryID(array[0]::class).toInt())
    val writeFunc = writeFunctions[array[0]::class]

    writeFunc ?: throw IllegalArgumentException("${array[0]::class} is not a known type!")

    array.forEach { arrayElement ->
        writeFunc(this, arrayElement)
    }
}

fun DataOutput.writeCompound(compound : Compound) {

    this.writeInt(compound.entries.size)

    println(">> (${compound.entries.size})")

    compound.forEach {

        val key = it.key
        val value = it.value
        val writeFunc = writeFunctions[it.value::class]

        writeFunc ?: throw IllegalArgumentException("${it.value::class} is not a known type!")

        this.writeUTF(it.key)
        this.writeByte(binaryID(it.value::class).toInt())

        println(">> ${it.key}: ${binaryID(it.value::class)}")

        writeFunc(this, it.value)
    }
}