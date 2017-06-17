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
            "Startup" to 1,
            "Root" to compoundOf(
                    "Dangerous" to false
            ),
            "Languages" to arrayOf(
                    "Java",
                    "Kotlin",
                    "Scala"
            )
    )

    BinaryIO.write(c, appHomeTestFile)
    val rc = BinaryIO.read(appHomeTestFile)

    println((rc["Languages"] as Array<String>)[1])

}

fun File.create() {
    this.createNewFile()
}

class Test {
}