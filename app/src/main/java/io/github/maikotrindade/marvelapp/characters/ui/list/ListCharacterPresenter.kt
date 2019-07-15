package io.github.maikotrindade.marvelapp.characters.ui.list

import android.util.Log
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BasePresenter
import io.github.maikotrindade.marvelapp.characters.domain.api.CharactersService
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.github.maikotrindade.marvelapp.persistence.CharacterRepository
import io.github.maikotrindade.marvelapp.util.EncryptUtil.getEpochTime
import io.github.maikotrindade.marvelapp.util.EncryptUtil.getHash
import io.github.maikotrindade.marvelapp.util.MobileConnection
import io.github.maikotrindade.marvelapp.util.SharedUtils
import io.github.maikotrindade.marvelapp.util.publicKey

class ListCharacterPresenter (private val view: ListCharacterView) : BasePresenter(), ListAdapterCharacterView {

    fun getCharacters(offset: Int = 0) {
        if (SharedUtils.getConnectionType() == MobileConnection.NO_INTERNET) {
            view.onRequestError(R.string.no_internet)
        } else {
            view.onRequestStared()
            requestCharactersServer(offset)
        }
    }

    private fun requestCharactersServer(offset: Int) {
        val timestamp = getEpochTime()
        val hash = getHash(timestamp)

        val disposable = CharactersService.requestCharacters(timestamp, publicKey, hash!!, offset)
            .subscribe({ res ->
                res.body()?.let {
                    val characters = it.data.results.toMutableList()
                     if (characters.isNotEmpty() && offset == 0) {
                         view.onRequestSuccess(characters)
                    } else if (characters.isNotEmpty() && offset > 0) {
                         view.onRequestSuccess(characters, true)
                    } else if (characters.isEmpty() && offset > 0) {
                         view.onRequestFinished()
                    }

                }
            }) { err ->
                view.onRequestError(R.string.generic_error)
                Log.d(ListCharacterPresenter::class.java.simpleName, err.message)
            }
        compositeDisposable.add(disposable)
    }

    override fun onSelectCharacter(character: Character) {
        view.navigateToDetails(character)
    }

    override fun onFavoriteCharacter(character: Character) {
        val dataRepository = CharacterRepository()
        dataRepository.insert(character)
    }

}