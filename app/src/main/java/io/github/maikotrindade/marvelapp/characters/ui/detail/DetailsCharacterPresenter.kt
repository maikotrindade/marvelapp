package io.github.maikotrindade.marvelapp.characters.ui.detail

import android.util.Log
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BasePresenter
import io.github.maikotrindade.marvelapp.characters.domain.api.CharactersService
import io.github.maikotrindade.marvelapp.util.EncryptUtil.getEpochTime
import io.github.maikotrindade.marvelapp.util.EncryptUtil.getHash
import io.github.maikotrindade.marvelapp.util.MobileConnection
import io.github.maikotrindade.marvelapp.util.SharedUtils
import io.github.maikotrindade.marvelapp.util.publicKey

class DetailsCharacterPresenter(private val view: DetailsCharacterView) : BasePresenter() {

    fun getComicsSeries(characterId: String) {
        if (SharedUtils.getConnectionType() == MobileConnection.NO_INTERNET) {
            view.onRequestError(R.string.no_internet)
        } else {
            view.onRequestStared()
            requestComicsServer(characterId)
            requestSeriesServer(characterId)
        }
    }

    private fun requestComicsServer(characterId: String) {
        val timestamp = getEpochTime()
        val hash = getHash(timestamp)

        val disposable = CharactersService.requestComics(timestamp, publicKey, hash!!, characterId)
            .subscribe({ res ->
                res.body()?.let {
                    view.onRequestComicsSuccess(it)
                }
            }) { err ->
                view.onRequestError(R.string.network_error)
                Log.d(DetailsCharacterPresenter::class.java.simpleName, err.message)
            }
        compositeDisposable?.add(disposable)
    }

    private fun requestSeriesServer(characterId: String) {
        val timestamp = getEpochTime()
        val hash = getHash(timestamp)

        val disposable = CharactersService.requestSeries(timestamp, publicKey, hash!!, characterId)
            .subscribe({ res ->
                res.body()?.let {
                    view.onRequestSeriesSuccess(it)
                }
            }) { err ->
                view.onRequestError(R.string.network_error)
                Log.d(DetailsCharacterPresenter::class.java.simpleName, err.message)
            }
        compositeDisposable?.add(disposable)
    }

}