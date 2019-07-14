package io.github.maikotrindade.marvelapp.util

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest

object EncryptUtil {

    fun getEpochTime(): String {
        return (System.currentTimeMillis() / 10000L).toString()
    }

    fun getHash(timestamp: String): String? {
        //https://developer.marvel.com/documentation/authorization
        val baseString = timestamp + privateKey + publicKey

        var hash: String? = null
        try {
            val messageDigest = MessageDigest.getInstance("MD5")
            messageDigest!!.update(baseString.toByteArray(), 0, baseString.length)
            hash = BigInteger(1, messageDigest.digest()).toString(16)
        } catch (e: Throwable) {
            Log.e(EncryptUtil::class.java.simpleName, e.localizedMessage)
        }
        return hash
    }

}