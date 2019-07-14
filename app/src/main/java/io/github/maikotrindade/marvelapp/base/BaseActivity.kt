package io.github.maikotrindade.marvelapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

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