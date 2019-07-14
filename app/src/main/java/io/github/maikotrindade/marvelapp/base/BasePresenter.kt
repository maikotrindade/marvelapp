package io.github.maikotrindade.marvelapp.base

import io.reactivex.disposables.CompositeDisposable

interface BasePresenterInterface {
    fun unsubscribe()
    fun createCompositeDisposable()
}

abstract class BasePresenter : BasePresenterInterface {

    var compositeDisposable: CompositeDisposable? = null

    override fun unsubscribe() {
        compositeDisposable = CompositeDisposable()
    }

    override fun createCompositeDisposable() {
        compositeDisposable?.clear()
    }
}
