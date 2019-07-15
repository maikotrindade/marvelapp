package io.github.maikotrindade.marvelapp.characters.view.list

import io.github.maikotrindade.marvelapp.base.BaseApp
import io.github.maikotrindade.marvelapp.database.CharacterRepository
import java.lang.ref.WeakReference
import javax.inject.Inject

enum class ListCharacterConfigurator {

    INSTANCE;

    @Inject
    lateinit var repository: CharacterRepository

    fun configure(fragment: ListCharactersFragment) {
        BaseApp.applicationContext().getComponent()!!.inject(this)
        val presenter = ListCharacterPresenter()
        presenter.view = WeakReference(fragment)
        presenter.dataRepository = WeakReference(repository)
        presenter.application = WeakReference(fragment.activity!!.application)
        fragment.presenter = presenter
    }

}