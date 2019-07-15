package io.github.maikotrindade.marvelapp.characters.ui.list

import io.github.maikotrindade.marvelapp.characters.domain.model.Character

interface FavoriteCharacterView {
    fun onRequestStared()
    fun onGetCharactersSuccess(characters: List<Character>)
    fun onGetCharactersError(messageResourceId: Int)
    fun onFavoriteRemoved(position: Int)
    fun navigateToDetails(character: Character)
}

interface FavoriteAdapterCharacterView {
    fun onSelectCharacter(character: Character)
    fun onNotFavoriteCharacter(character: Character, position: Int)

}