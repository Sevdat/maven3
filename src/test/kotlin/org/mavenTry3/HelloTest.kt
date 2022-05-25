package org.mavenTry3
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class EncryptionKtTest {

    @Test
    private fun assert(name: Unit, expectedContent: String) {
        File("src/test/kotlin/Encryption.txt").writeText("000\r\n000")
        val file = File("src/test/kotlin/Encryption.txt_encrypted")
        val content = file.readLines().joinToString()
        assertEquals(expectedContent, content)
    }
    @Test
    fun encrypt() {
        assert(
            encrypt(File("src/test/kotlin/Encryption.txt"),"6B"),
            "[[[fa[[["
        )
        assert(
            encrypt(File("src/test/kotlin/Encryption.txt"),"6B 73"),
            "[C[~aC[C"
        )
    }

}