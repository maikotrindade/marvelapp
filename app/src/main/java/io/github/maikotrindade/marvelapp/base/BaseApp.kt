package io.github.maikotrindade.marvelapp.base

import android.app.Application
import io.github.maikotrindade.marvelapp.di.components.AppComponent
import io.github.maikotrindade.marvelapp.di.components.DaggerAppComponent
import io.github.maikotrindade.marvelapp.di.modules.RoomModule


class BaseApp : Application() {

    private lateinit var component: AppComponent

    init {
        instance = this
    }

    companion object {
        private var instance: BaseApp? = null

        fun applicationContext(): BaseApp {
            return instance as BaseApp
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .roomModule(RoomModule(this))
            .build()
    }

    fun getComponent(): AppComponent? {
        return component
    }

}
