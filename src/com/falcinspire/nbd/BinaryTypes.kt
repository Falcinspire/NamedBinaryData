package com.falcinspire.nbd

import kotlin.reflect.KClass

/**
 * @author Falcinspire
 * @version Jun/14/2017 (12:24 AM)
 */

fun binaryID(clazz : KClass<*>) : Byte {
    return BinaryTypes.fromType[clazz] ?: throw IllegalArgumentException("${clazz} is not a supported data type!")
}

fun binaryType(id : Byte) : KClass<*> {
    return BinaryTypes.fromID[id] ?: throw IllegalArgumentException("${id} is not a supported id!")
}

fun validType(clazz : KClass<*>) : Boolean {
    BinaryTypes.ordinal.forEach {
        if (it.clazz == clazz) return true
    }
    return false
}

enum class BinaryTypes(val clazz : KClass<*>, val id : Byte) {
    COMPOUND(Compound::class, 1.toByte()),
    ARRAY(Array<Any>::class, 2.toByte()),
    BOOLEAN(Boolean::class, 3.toByte()),
    BYTE(Byte::class, 4.toByte()),
    SHORT(Short::class, 5.toByte()),
    INT(Int::class, 6.toByte()),
    LONG(Long::class, 7.toByte()),
    FLOAT(Float::class, 8.toByte()),
    DOUBLE(Double::class, 9.toByte()),
    STRING(String::class, 10.toByte()),
    ARRAY_BOOLEAN(BooleanArray::class, 11.toByte()),
    ARRAY_BYTE(ByteArray::class, 12.toByte()),
    ARRAY_SHORT(ShortArray::class, 13.toByte()),
    ARRAY_INT(IntArray::class, 14.toByte()),
    ARRAY_LONG(LongArray::class, 15.toByte()),
    ARRAY_FLOAT(FloatArray::class, 16.toByte()),
    ARRAY_DOUBLE(DoubleArray::class, 17.toByte()),
    ARRAY_STRING(Array<String>::class, 18.toByte());

    companion object {
        val ordinal = BinaryTypes.values()
        val fromID = HashMap<Byte, KClass<*>>(ordinal.size)
        val fromType = HashMap<KClass<*>, Byte>(ordinal.size)

        init {
            repeat (ordinal.size) {index ->
                val type = ordinal[index]
                fromID.put(type.id, type.clazz)
                fromType.put(type.clazz, type.id)
            }
        }
    }
}
    