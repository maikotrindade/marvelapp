package io.github.maikotrindade.marvelapp.di.components

import dagger.Component
import io.github.maikotrindade.marvelapp.characters.view.favorite.FavoriteCharacterConfigurator
import io.github.maikotrindade.marvelapp.characters.view.list.ListCharacterConfigurator
import io.github.maikotrindade.marvelapp.di.modules.RoomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])

interface AppComponent {
    fun inject(configurator: FavoriteCharacterConfigurator)
    fun inject(configurator: ListCharacterConfigurator)
}