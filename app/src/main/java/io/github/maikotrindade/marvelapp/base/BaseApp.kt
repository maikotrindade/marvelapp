package io.github.maikotrindade.marvelapp.base

import android.app.Application

class BaseApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: BaseApp? = null

        fun applicationContext(): BaseApp {
            return instance as BaseApp
        }
    }

}
