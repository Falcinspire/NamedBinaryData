package com.falcinspire.nbd

import java.io.*

/**
 * @author Falcinspire
 * @version Jun/15/2017 (7:56 PM)
 */

object BinaryIO {

    fun write(compound : Compound, oStream: OutputStream)
            = DataOutputStream(BufferedOutputStream(oStream)).closeAfter { it.writeCompound(compound) }

    fun write(compound : Compound, file : File)
            = write(compound, FileOutputStream(file))

    fun read(iStream: InputStream) : Compound
            = DataInputStream(BufferedInputStream(iStream)).closeAfter { it.readCompound() }

    fun read(file : File) : Compound
            = read(FileInputStream(file))
}