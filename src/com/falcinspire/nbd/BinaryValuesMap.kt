package com.falcinspire.nbd

import kotlin.reflect.KClass

/**
 * @author Falcinspire
 * @version Jun/14/2017 (12:24 AM)
 */

fun binaryID(clazz : KClass<*>) : Byte {
    return typeToID[clazz] ?: throw IllegalArgumentException("${clazz} is not a supported data type!")
}

val typeToID = mapOf<KClass<*>, Byte>(
        HashMap::class to 1,
        Array<Any>::class to 2,
        Boolean::class to 3,
        Byte::class to 4,
        Short::class to 5,
        Int::class to 6,
        Long::class to 7,
        Float::class to 8,
        Double::class to 9,
        String::class to 10,
        BooleanArray::class to 11,
        ByteArray::class to 12,
        ShortArray::class to 13,
        IntArray::class to 14,
        LongArray::class to 15,
        FloatArray::class to 16,
        DoubleArray::class to 17,
        Array<String>::class to 18
)
    