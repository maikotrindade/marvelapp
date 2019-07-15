package io.github.maikotrindade.marvelapp.characters.view.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BaseFragment
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.github.maikotrindade.marvelapp.characters.view.detail.DetailsCharacterActivity
import io.github.maikotrindade.marvelapp.util.basePagination
import kotlinx.android.synthetic.main.fragment_list_characters.*


class ListCharactersFragment : BaseFragment(), ListCharacterView {

    lateinit var presenter: ListCharacterPresenter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: ListCharacterAdapter
    private var isLoading = false

    companion object {
        fun newInstance(): ListCharactersFragment {
            return ListCharactersFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_characters, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ListCharacterConfigurator.INSTANCE.configure(this)
        presenter.getCharacters()
        setupUI()
    }

    private fun setupUI() {
        swipeRefreshCharacters.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        swipeRefreshCharacters.setOnRefreshListener {
            presenter.getCharacters()
        }

        adapter = ListCharacterAdapter(presenter, ArrayList())
        rvCharacters.adapter = adapter
        layoutManager = GridLayoutManager(requireContext(), 2)
        rvCharacters.layoutManager = layoutManager
        rvCharacters.setHasFixedSize(true)
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
             var pastVisibleItems: Int = 0
             var visibleItemCount: Int = 0
             var totalItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (!isLoading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            Log.d("VAI", "visibleItemCount: " + visibleItemCount)
                            Log.d("VAI", "pastVisibleItems: " + pastVisibleItems)
                            Log.d("VAI", "totalItemCount: " + totalItemCount)
                            Log.d("VAI", "---------------------------------------")
                            isLoading = true
                            presenter.getCharacters(totalItemCount)
                        }
                    }
                }
            }
        })

    }

    override fun onRequestSuccess(characters: MutableList<Character>, isPagination: Boolean) {
        onRequestFinished()
        if (isPagination) {
            adapter.addMoreItems(characters)
        } else {
            adapter.setItems(characters)
        }
        isLoading = characters.size < basePagination
    }

    override fun onRequestError(resourceId: Int) {
        onRequestFinished()
        Snackbar.make(containerView, getString(resourceId), Snackbar.LENGTH_SHORT)
            .setActionTextColor(ContextCompat.getColor(activity!!, R.color.md_red_400))
            .show()
    }

    override fun onRequestStared() {
        isLoading = true
        swipeRefreshCharacters.isRefreshing = true
    }

    override fun onRequestFinished() {
        isLoading = false
        if (swipeRefreshCharacters.isRefreshing) {
            swipeRefreshCharacters.isRefreshing = false
        }
    }

    override fun navigateToDetails(character: Character) {
        val intent = Intent(context, DetailsCharacterActivity::class.java)
        intent.putExtra("toDetailsBundle", character)
        startActivity(intent)
    }

}
