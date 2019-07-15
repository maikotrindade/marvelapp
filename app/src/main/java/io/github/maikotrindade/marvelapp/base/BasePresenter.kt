package io.github.maikotrindade.marvelapp.base

import io.reactivex.disposables.CompositeDisposable

interface BasePresenterInterface {
    fun unsubscribe()
    fun createCompositeDisposable()
}

abstract class BasePresenter : BasePresenterInterface {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun unsubscribe() {
        compositeDisposable.dispose()
    }

    override fun createCompositeDisposable() {
        compositeDisposable.clear()
    }
}
