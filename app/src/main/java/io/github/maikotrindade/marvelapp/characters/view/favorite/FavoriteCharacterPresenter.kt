package io.github.maikotrindade.marvelapp.characters.view.favorite

import android.util.Log
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BasePresenter
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.github.maikotrindade.marvelapp.database.CharacterRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class FavoriteCharacterPresenter : BasePresenter(),
    FavoriteAdapterCharacterView {

    lateinit var dataRepository: WeakReference<CharacterRepository>
    lateinit var view: WeakReference<FavoriteCharacterView>

    fun getFavoriteCharacters() {
        val disposable = dataRepository.get()?.getAllCharacterCharacter()!!
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view.get()?.onGetCharactersSuccess(it) },
                { e ->
                    Log.e(FavoriteCharacterPresenter::class.java.simpleName, e.localizedMessage)
                    view.get()?.onGetCharactersError(R.string.generic_error)
                })

        compositeDisposable.add(disposable)
    }

    override fun onSelectCharacter(character: Character) {
        view.get()?.navigateToDetails(character)
    }

    override fun onNotFavoriteCharacter(character: Character, position: Int) {
        dataRepository.get()?.delete(character)
        view.get()?.onFavoriteRemoved(position)
    }
}