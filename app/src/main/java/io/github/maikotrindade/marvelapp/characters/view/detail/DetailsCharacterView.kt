package io.github.maikotrindade.marvelapp.characters.view.detail

import io.github.maikotrindade.marvelapp.characters.domain.model.Comic
import io.github.maikotrindade.marvelapp.characters.domain.model.Series

interface DetailsCharacterView {

    fun onRequestStared()
    fun onRequestComicsSuccess(comics: List<Comic>)
    fun onRequestSeriesSuccess(series: List<Series>)
    fun onRequestError(resourceId: Int)

}