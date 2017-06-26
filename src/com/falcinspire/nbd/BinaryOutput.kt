package com.falcinspire.nbd

import java.io.DataOutput
import kotlin.reflect.KClass

/**
 * @author Falcinspire
 * @version Jun/14/2017 (12:12 AM)
 */

fun writeFunctionForClass(obj : Any) : ((DataOutput, Any) -> Unit)? {
    writeFunctions.forEach {
        if (it.key.isInstance(obj)) return it.value
    }
    throw IllegalArgumentException("${obj::class.simpleName} is not a supported data type!")
}

val writeFunctions = mapOf<KClass<*>, (DataOutput, Any) -> Unit>(
        BinaryTypes.COMPOUND.clazz to {output, value -> output.writeCompound((value as Compound))},
        BinaryTypes.LIST.clazz to {output, value -> output.writeList((value as List<Any>))},
        BinaryTypes.BOOLEAN.clazz to {output, value -> output.writeBoolean((value as Boolean))},
        BinaryTypes.BYTE.clazz to {output, value -> output.writeByte((value as Byte).toInt())},
        BinaryTypes.SHORT.clazz to {output, value -> output.writeShort((value as Short).toInt())},
        BinaryTypes.INT.clazz to {output, value -> output.writeInt((value as Int))},
        BinaryTypes.LONG.clazz to {output, value -> output.writeLong((value as Long))},
        BinaryTypes.FLOAT.clazz to {output, value -> output.writeFloat((value as Float))},
        BinaryTypes.DOUBLE.clazz to {output, value -> output.writeDouble((value as Double))},
        BinaryTypes.STRING.clazz to {output, value -> output.writeUTF((value as String))},
        BinaryTypes.ARRAY_BOOLEAN.clazz to {output, value -> output.writeBooleanArray((value as BooleanArray))},
        BinaryTypes.ARRAY_BYTE.clazz to {output, value -> output.writeByteArray((value as ByteArray))},
        BinaryTypes.ARRAY_SHORT.clazz to {output, value -> output.writeShortArray((value as ShortArray))},
        BinaryTypes.ARRAY_INT.clazz to { output, value -> output.writeIntArray((value as IntArray))},
        BinaryTypes.ARRAY_LONG.clazz to { output, value -> output.writeLongArray((value as LongArray))},
        BinaryTypes.ARRAY_FLOAT.clazz to { output, value -> output.writeFloatArray((value as FloatArray))},
        BinaryTypes.ARRAY_DOUBLE.clazz to {output, value -> output.writeDoubleArray((value as DoubleArray))},
        BinaryTypes.ARRAY_STRING.clazz to {output, value -> output.writeStringArray((value as Array<String>))}
)

fun DataOutput.writeList(list: List<Any>) {

    if (list.size == 0) return

    this.writeInt(list.size)
    this.writeByte(binaryID(list[0]).toInt())
    val writeFunc = writeFunctionForClass(list[0])

    writeFunc ?: throw IllegalArgumentException("${list[0]::class} is not a known type!")

    list.forEach { arrayElement ->
        writeFunc(this, arrayElement)
    }
}

fun DataOutput.writeCompound(compound : Compound) {

    this.writeInt(compound.entries.size)

    println(">> (${compound.entries.size})")

    compound.forEach {

        val key = it.key
        val value = it.value
        val writeFunc = writeFunctionForClass(it.value)

        writeFunc ?: throw IllegalArgumentException("${it.value::class.simpleName} is not a known type!")

        this.writeUTF(it.key)
        this.writeByte(binaryID(it.value).toInt())

        println(">> ${it.key}: ${binaryID(it.value)}")

        writeFunc(this, it.value)
    }
}