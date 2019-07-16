package io.github.maikotrindade.marvelapp.characters.view.list

import android.app.Application
import android.util.Log
import io.github.maikotrindade.marvelapp.base.BasePresenter
import io.github.maikotrindade.marvelapp.characters.domain.api.CharactersService
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.github.maikotrindade.marvelapp.database.CharacterRepository
import io.github.maikotrindade.marvelapp.util.EncryptUtil.getEpochTime
import io.github.maikotrindade.marvelapp.util.EncryptUtil.getHash
import io.github.maikotrindade.marvelapp.util.MobileConnection
import io.github.maikotrindade.marvelapp.util.SharedUtils
import io.github.maikotrindade.marvelapp.util.publicKey
import java.lang.ref.WeakReference


class ListCharacterPresenter: BasePresenter(), ListAdapterCharacterView {

    lateinit var dataRepository: WeakReference<CharacterRepository>
    lateinit var view: WeakReference<ListCharacterView>
    lateinit var application: WeakReference<Application>

    fun getCharacters(offset: Int = 0) {
        if (SharedUtils.getConnectionType(application.get()!!) == MobileConnection.NO_INTERNET) {
            view.get()?.onRequestError(io.github.maikotrindade.marvelapp.R.string.no_internet)
        } else {
            view.get()?.onRequestStared()
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
                         view.get()?.onRequestSuccess(characters)
                    } else if (characters.isNotEmpty() && offset > 0) {
                         view.get()?.onRequestSuccess(characters, true)
                    } else {
                         view.get()?.onRequestFinished()
                    }
                }
            }) { err ->
                view.get()?.onRequestError(io.github.maikotrindade.marvelapp.R.string.generic_error)
                Log.d(ListCharacterPresenter::class.java.simpleName, err.message)
            }
        compositeDisposable.add(disposable)
    }

    override fun onSelectCharacter(character: Character) {
        view.get()?.navigateToDetails(character)
    }

    override fun onFavoriteCharacter(character: Character) {
        dataRepository.get()?.insert(character)
    }

}