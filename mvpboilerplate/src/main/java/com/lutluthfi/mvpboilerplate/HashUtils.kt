package com.lutluthfi.mvpboilerplate

import android.util.Log

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object HashUtils {

    private val TAG = HashUtils::class.java.simpleName

    @Throws(NoSuchAlgorithmException::class)
    fun hashWithMD5(input: String?): String? {
        var result = input
        if (input != null) {
            val md = MessageDigest.getInstance("MD5") // or "SHA-1"
            md.update(input.toByteArray())
            val hash = BigInteger(1, md.digest())
            result = hash.toString(16)
            result?.let { it ->
                while (it.length < 32) {
                    result = "0$result"
                }
            }
        }
        Log.d(TAG, "hashWithMD5(): $result")
        return result
    }

    @Throws(NoSuchAlgorithmException::class)
    fun hashWithSHA1(input: String?): String? {
        var result = input
        if (input != null) {
            val md = MessageDigest.getInstance("SHA-1")
            md.update(input.toByteArray())
            val hash = BigInteger(1, md.digest())
            result = hash.toString(16)
            result?.let {
                while (it.length < 40) {
                    result = "0$result"
                }
            }
        }
        Log.d(TAG, "hashWithSHA1(): $result")
        return result
    }
}// This class is not publicly instantiate
