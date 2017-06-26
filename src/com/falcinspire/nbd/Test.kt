package com.falcinspire.nbd

import java.io.File

/**
 * @author Falcinspire
 * @version Jun/15/2017 (7:53 PM)
 */

fun main(args : Array<String>) {

    var home = System.getProperty("user.home")
    val appHome = File(home, "/NamedBinaryData")
    if (!appHome.exists()) appHome.mkdir()
    val appHomeTestFile = File(appHome, "test.nbd")
    if (!appHomeTestFile.exists()) appHomeTestFile.create()

    val c = compoundOf(
            "Boolean" to true,
            "Byte" to 0.toByte(),
            "Short" to 1.toShort(),
            "Int" to 2,
            "Long" to 3.toLong(),
            "Float" to 4.toFloat(),
            "Double" to 5.toDouble(),
            "String" to "NeoMc_",
            "Boolean[]" to BooleanArray(7),
            "Byte[]" to ByteArray(7),
            "Short[]" to ShortArray(7),
            "Int[]" to IntArray(7),
            "Long[]" to LongArray(7),
            "Float[]" to FloatArray(7),
            "Double[]" to DoubleArray(7),
            "String[]" to Array(7){"Falcinspire"},
            "Compound" to compoundOf(
                    "Boolean" to true,
                    "Byte" to 0.toByte(),
                    "Short" to 1.toShort(),
                    "Int" to 2,
                    "Long" to 3.toLong(),
                    "Float" to 4.toFloat(),
                    "Double" to 5.toDouble(),
                    "String" to 6.toString(),
                    "Boolean[]" to BooleanArray(7),
                    "Byte[]" to ByteArray(7),
                    "Short[]" to ShortArray(7),
                    "Int[]" to IntArray(7),
                    "Long[]" to LongArray(7),
                    "Float[]" to FloatArray(7),
                    "Double[]" to DoubleArray(7),
                    "String[]" to Array<String>(7){"Falcinspire"},
                    "List" to listOf(
                            simpleCompound(),
                            simpleCompound()
                    )
            ),
            "List" to listOf(
                    simpleCompound(),
                    simpleCompound()
            )
    )

    BinaryIO.write(c, appHomeTestFile)
    val rc = BinaryIO.read(appHomeTestFile)

    println((rc["Boolean"]))
    println((rc["Byte"]))
    println((rc["Short"]))
    println((rc["Int"]))
    println((rc["List"] as List<Compound>)[0]["Boolean"])

}

fun simpleCompound() : Compound {
    return compoundOf(
            "Boolean" to true,
            "Byte" to 0.toByte(),
            "Short" to 1.toShort(),
            "Int" to 2,
            "Long" to 3.toLong(),
            "Float" to 4.toFloat(),
            "Double" to 5.toDouble(),
            "String" to 6.toString(),
            "Boolean[]" to BooleanArray(7),
            "Byte[]" to ByteArray(7),
            "Short[]" to ShortArray(7),
            "Int[]" to IntArray(7),
            "Long[]" to LongArray(7),
            "Float[]" to FloatArray(7),
            "Double[]" to DoubleArray(7),
            "String[]" to Array<String>(7){"Falcinspire"}
    )
}

fun File.create() {
    this.createNewFile()
}