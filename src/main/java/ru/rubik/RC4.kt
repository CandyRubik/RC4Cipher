package ru.rubik

class RC4(
        private val plainText: String,
        private val key: String,
) {
    private var init: IntArray = IntArray(BYTE_SIZE)
    private var keyArray: ByteArray = ByteArray(BYTE_SIZE)

    fun encryptionRC4(plainText: String): String {
        initArray(init, keyArray, key)
        swapping(init, keyArray)
        return encryption(plainText)
    }

    private fun initArray(init: IntArray, swap: ByteArray, key: String) {
        for (i in 0 until BYTE_SIZE) {
            init[i] = i
            swap[i] = key[i % key.length].code.toByte()
        }
    }

    private fun swapping(init: IntArray, swap: ByteArray) {
        var j = 0
        for (i in 0 until BYTE_SIZE) {
            j = (j + init[i] + swap[i]) % BYTE_SIZE
            swap(init, i, j)
        }
    }

    private fun swap(init: IntArray, i: Int, j: Int) {
        val temp = init[i]
        init[i] = init[j]
        init[j] = temp
    }

    private fun encryption(plainText: String): String {
        val input = plainText.toCharArray()
        val keyStream = CharArray(input.size)
        var i = 0
        var j = 0
        for (cnt in plainText.indices) {
            i = (i + 1) % BYTE_SIZE
            j = (j + init[i]) % BYTE_SIZE
            swap(init, i, j)
            val t = (init[i] + init[j]) % BYTE_SIZE
            val stream = init[t].toChar()
            keyStream[cnt] = (input[cnt].code xor stream.code).toChar()
        }
        return String(keyStream)
    }

    companion object {
        const val BYTE_SIZE = 256
    }
}