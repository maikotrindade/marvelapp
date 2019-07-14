package io.github.maikotrindade.marvelapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    var basePresenter: BasePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basePresenter?.createCompositeDisposable()
    }

    override fun onStop() {
        super.onStop()
        basePresenter?.unsubscribe()
    }
}