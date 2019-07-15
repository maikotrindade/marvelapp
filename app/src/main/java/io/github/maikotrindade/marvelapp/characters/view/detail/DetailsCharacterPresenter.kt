package io.github.maikotrindade.marvelapp.characters.view.detail

import android.app.Application
import android.util.Log
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BasePresenter
import io.github.maikotrindade.marvelapp.characters.domain.api.CharactersService
import io.github.maikotrindade.marvelapp.util.EncryptUtil.getEpochTime
import io.github.maikotrindade.marvelapp.util.EncryptUtil.getHash
import io.github.maikotrindade.marvelapp.util.MobileConnection
import io.github.maikotrindade.marvelapp.util.SharedUtils
import io.github.maikotrindade.marvelapp.util.publicKey
import java.lang.ref.WeakReference

class DetailsCharacterPresenter : BasePresenter() {

    lateinit var view: WeakReference<DetailsCharacterView>
    lateinit var application: WeakReference<Application>

    fun getComicsSeries(characterId: String) {
        if (SharedUtils.getConnectionType(application.get()!!) == MobileConnection.NO_INTERNET) {
            view.get()?.onRequestError(R.string.no_internet)
        } else {
            view.get()?.onRequestStared()
            requestComicsServer(characterId)
            requestSeriesServer(characterId)
        }
    }

    fun requestComicsServer(characterId: String) {
        val timestamp = getEpochTime()
        val hash = getHash(timestamp)

        val disposable = CharactersService.requestComics(timestamp, publicKey, hash!!, characterId)
            .subscribe({ res ->
                res.body()?.let {
                    view.get()?.onRequestComicsSuccess(it.data.results)
                }
            }) { err ->
                view.get()?.onRequestError(R.string.generic_error)
                Log.d(DetailsCharacterPresenter::class.java.simpleName, err.message)
            }
        compositeDisposable.add(disposable)
    }

    fun requestSeriesServer(characterId: String) {
        val timestamp = getEpochTime()
        val hash = getHash(timestamp)

        val disposable = CharactersService.requestSeries(timestamp, publicKey, hash!!, characterId)
            .subscribe({ res ->
                res.body()?.let {
                    view.get()?.onRequestSeriesSuccess(it.data.results)
                }
            }) { err ->
                view.get()?.onRequestError(R.string.generic_error)
                Log.d(DetailsCharacterPresenter::class.java.simpleName, err.message)
            }
        compositeDisposable.add(disposable)
    }

}