package io.github.maikotrindade.marvelapp.characters.ui.list

import android.util.Log
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BasePresenter
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.github.maikotrindade.marvelapp.persistence.CharacterRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteCharacterPresenter(private val view: FavoriteCharacterView) :
    BasePresenter(), FavoriteAdapterCharacterView {


    fun getFavoriteCharacters() {
        val dataRepository = CharacterRepository()
        val disposable = dataRepository.getAllCharacterCharacter()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ view.onGetCharactersSuccess(it) },
                { e ->
                    Log.e(FavoriteCharacterPresenter::class.java.simpleName, e.localizedMessage)
                    view.onGetCharactersError(R.string.generic_error)
                })

        compositeDisposable.add(disposable)
    }

    override fun onSelectCharacter(character: Character) {
        view.navigateToDetails(character)
    }

    override fun onNotFavoriteCharacter(character: Character, position: Int) {
        val dataRepository = CharacterRepository()
        dataRepository.delete(character)
        view.onFavoriteRemoved(position)
    }
}