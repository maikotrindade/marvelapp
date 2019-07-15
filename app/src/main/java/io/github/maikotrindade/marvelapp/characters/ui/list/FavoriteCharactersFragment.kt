package io.github.maikotrindade.marvelapp.characters.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.github.maikotrindade.marvelapp.base.BaseFragment
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.github.maikotrindade.marvelapp.characters.ui.detail.DetailsCharacterActivity
import kotlinx.android.synthetic.main.fragment_favorite_characters.*

class FavoriteCharactersFragment : BaseFragment(), FavoriteCharacterView {

    private val presenter = FavoriteCharacterPresenter(this)
    private lateinit var adapter: FavoriteCharacterAdapter

    companion object {
        fun newInstance(): FavoriteCharactersFragment {
            return FavoriteCharactersFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(io.github.maikotrindade.marvelapp.R.layout.fragment_favorite_characters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        presenter.getFavoriteCharacters()
    }

    private fun setupUI() {
        swipeRefreshFavorites.setColorSchemeColors(ContextCompat.getColor(requireContext(), io.github.maikotrindade.marvelapp.R.color.colorPrimary))
        swipeRefreshFavorites.setOnRefreshListener {
            presenter.getFavoriteCharacters()
        }

        rvFavorites.layoutManager = GridLayoutManager(requireContext(), 2)
        rvFavorites.setHasFixedSize(true)
    }

    override fun onGetCharactersSuccess(characters: List<Character>) {
        onRequestFinished()
        adapter = FavoriteCharacterAdapter(presenter, characters.toMutableList())
        rvFavorites.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onGetCharactersError(resourceId: Int) {
        onRequestFinished()
        Snackbar.make(containerView, getString(resourceId), Snackbar.LENGTH_SHORT)
            .setActionTextColor(ContextCompat.getColor(activity!!, io.github.maikotrindade.marvelapp.R.color.md_red_400))
            .show()
    }

    override fun onRequestStared() {
        swipeRefreshFavorites.isRefreshing = true
    }

    private fun onRequestFinished() {
        if (swipeRefreshFavorites.isRefreshing) {
            swipeRefreshFavorites.isRefreshing = false
        }
    }

    override fun onFavoriteRemoved(position: Int) {
        adapter.removeAt(position)
    }

    override fun navigateToDetails(character: Character) {
        val intent = Intent(context, DetailsCharacterActivity::class.java)
        intent.putExtra("toDetailsBundle", character)
        startActivity(intent)
    }
}
