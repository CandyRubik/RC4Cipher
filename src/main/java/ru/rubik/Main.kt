package ru.rubik

fun main() {
    println("Введите текст для кодирования:")
    val plainText = readln()
    val key = "keyRC4"
    val rc4 = RC4(plainText, key)

    val E = rc4.encryptionRC4(plainText)

    println("encryption : $E")

    println("decryption : " + rc4.encryptionRC4(E))

}