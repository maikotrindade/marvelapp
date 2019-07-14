package io.github.maikotrindade.marvelapp.characters.ui.detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BaseActivity
import io.github.maikotrindade.marvelapp.characters.domain.model.*
import kotlinx.android.synthetic.main.activity_details_character.*

class DetailsCharacterActivity : BaseActivity(), DetailsCharacterView {

    private val presenter = DetailsCharacterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_character)
        setupUI()
    }

    private fun setupUI() {
        val character: Character = intent.extras.getParcelable("toDetailsBundle")
        setupCharacterInfo(character)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        swipeRefreshDetails.setColorSchemeColors(ContextCompat.getColor(baseContext, R.color.colorPrimary))
        swipeRefreshDetails.setOnRefreshListener {
            presenter.getComicsSeries(character.id)
        }
        setupLists()
        presenter.getComicsSeries(character.id)
    }

    private fun setupCharacterInfo(character: Character) {
        toolbar_title.text = character.name

        txtDescription.text = character.description
        val imageUrl = character.thumbnail.path + "." + character.thumbnail.extension
        Glide
            .with(baseContext)
            .load(imageUrl)
            .centerCrop()
            .into(imgItem)
    }

    private fun setupLists() {
        rvComics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvComics.setHasFixedSize(true)

        rvSeries.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvSeries.setHasFixedSize(true)
    }

    override fun onRequestComicsSuccess(comicsResponse: ComicsResponse) {
        onRequestFinished()
        val comics = comicsResponse.data.results
        updateComicsList(comics)
    }

    override fun onRequestSeriesSuccess(seriesResponse: SeriesResponse) {
        val series = seriesResponse.data.results
        updateSeriesList(series)
    }

    private fun updateComicsList(comics: List<Comic>) {
        val adapter = ComicsAdapter(comics)
        rvComics.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun updateSeriesList(series: List<Series>) {
        val adapter = SeriesAdapter(series)
        rvSeries.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onRequestError(resourceId: Int) {
        onRequestFinished()
        Snackbar.make(containerView, getString(resourceId), Snackbar.LENGTH_SHORT)
            .setActionTextColor(ContextCompat.getColor(this@DetailsCharacterActivity, R.color.md_red_400))
            .show()
    }

    override fun onRequestStared() {
        swipeRefreshDetails.isRefreshing = true
    }

    private fun onRequestFinished() {
        if (swipeRefreshDetails.isRefreshing) {
            swipeRefreshDetails.isRefreshing = false
        }
    }
}
