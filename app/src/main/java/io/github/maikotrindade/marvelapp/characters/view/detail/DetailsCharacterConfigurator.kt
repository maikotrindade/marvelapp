package io.github.maikotrindade.marvelapp.characters.view.detail

import java.lang.ref.WeakReference

enum class DetailsCharacterConfigurator {

    INSTANCE;

    fun configure(activity: DetailsCharacterActivity) {
        val presenter = DetailsCharacterPresenter()
        presenter.view = WeakReference(activity)
        presenter.application = WeakReference(activity.application)
        activity.presenter = presenter
    }

}