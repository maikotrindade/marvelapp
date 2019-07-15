package io.github.maikotrindade.marvelapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.reactivex.Flowable

@Dao
interface CharacterDao {

    @get:Query("select * from character")
    val selectAll: Flowable<List<Character>>

    @Insert(onConflict = REPLACE)
    fun insert(character: Character)

    @Delete
    fun delete(character: Character)

}
