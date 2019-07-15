package io.github.maikotrindade.marvelapp.characters.view.favorite

import io.github.maikotrindade.marvelapp.base.BaseApp
import io.github.maikotrindade.marvelapp.database.CharacterRepository
import java.lang.ref.WeakReference
import javax.inject.Inject

enum class FavoriteCharacterConfigurator {

    INSTANCE;

    @Inject
    lateinit var repository: CharacterRepository

    fun configure(fragment: FavoriteCharactersFragment) {

        BaseApp.applicationContext().getComponent()!!.inject(this)
        val presenter = FavoriteCharacterPresenter()
        presenter.view = WeakReference(fragment)
        presenter.dataRepository = WeakReference(repository)
        fragment.presenter = presenter
    }

}