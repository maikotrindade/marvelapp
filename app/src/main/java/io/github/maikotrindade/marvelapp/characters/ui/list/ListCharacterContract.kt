package io.github.maikotrindade.marvelapp.characters.ui.list

import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.github.maikotrindade.marvelapp.characters.domain.model.CharactersResponse

interface ListCharacterContract {

    interface ListCharacterView {
        fun onRequestStared()
        fun onRequestSuccess(charactersResponse: CharactersResponse)
        fun onRequestError(resourceId: Int)
        fun navigateToDetails(character: Character)
    }

    interface ListAdapterCharacterView {
        fun onSelectCharacter(character: Character)
        fun onFavoriteCharacter(character: Character)
    }

}