package org.mavenTry3

import java.io.File
import kotlin.experimental.xor

fun encrypt(textFile: File, key: String) {
    val removeKeySpace = key.replace(" ", "")
    if (
        key.replace(Regex("""[0-9A-F ]"""), "").isNotEmpty() || removeKeySpace.length % 2 != 0
    )
        throw IllegalArgumentException("Key Error")

    val splitKey = removeKeySpace.split("").filter { e -> e != "" }
    val hexToByteList = mutableListOf<Byte>()
    var doubleChar = 0
    while (doubleChar <= splitKey.size / 2) {
        val hex = "${splitKey[doubleChar]}${splitKey[doubleChar + 1]}"
        val newKey = hex.toInt(16).toByte()
        hexToByteList += newKey
        doubleChar += 2
    }

    var encrypt: Byte
    var listIndex = 0
    File("${textFile}_encrypted").outputStream().use {
        for (letter in textFile.inputStream().readBytes()) {
            encrypt = letter xor hexToByteList[listIndex]
            listIndex += 1
            it.write(encrypt.toInt())
            if (listIndex == hexToByteList.size) listIndex = 0
        }
    }
}

