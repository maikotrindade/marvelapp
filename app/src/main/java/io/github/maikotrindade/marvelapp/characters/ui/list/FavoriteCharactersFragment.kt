package io.github.maikotrindade.marvelapp.characters.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BaseFragment

class FavoriteCharactersFragment : BaseFragment() {

    companion object {

        fun newInstance(): FavoriteCharactersFragment {
            return FavoriteCharactersFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_characters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


}
