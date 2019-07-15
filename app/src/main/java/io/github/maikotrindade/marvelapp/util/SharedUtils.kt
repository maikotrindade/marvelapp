package io.github.maikotrindade.marvelapp.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object SharedUtils {

    fun getConnectionType(context: Application): MobileConnection {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var result = MobileConnection.NO_INTERNET

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager?.run {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = MobileConnection.WIFI
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = MobileConnection.MOBILE
                    }
                }
            }
        } else {
            connectivityManager?.run {
                connectivityManager.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = MobileConnection.WIFI
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = MobileConnection.MOBILE
                    }
                }
            }
        }
        return result
    }

}

enum class MobileConnection(type: Int) {
    NO_INTERNET(0), MOBILE(1), WIFI(2)
}