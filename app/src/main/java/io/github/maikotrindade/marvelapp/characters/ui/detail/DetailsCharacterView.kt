package io.github.maikotrindade.marvelapp.characters.ui.detail

import io.github.maikotrindade.marvelapp.characters.domain.model.ComicsResponse
import io.github.maikotrindade.marvelapp.characters.domain.model.SeriesResponse

interface DetailsCharacterView {

    fun onRequestStared()
    fun onRequestComicsSuccess(comicsResponse: ComicsResponse)
    fun onRequestSeriesSuccess(seriesResponse: SeriesResponse)
    fun onRequestError(resourceId: Int)

}