package io.github.maikotrindade.marvelapp.characters.ui.list

import io.github.maikotrindade.marvelapp.characters.domain.model.Character

    interface ListCharacterView {
        fun onRequestStared()
        fun onRequestSuccess(characters: MutableList<Character>, isPagination: Boolean = false)
        fun onRequestError(resourceId: Int)
        fun onRequestFinished()
        fun navigateToDetails(character: Character)
    }

    interface ListAdapterCharacterView {
        fun onSelectCharacter(character: Character)
        fun onFavoriteCharacter(character: Character)
    }
