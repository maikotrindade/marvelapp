package io.github.maikotrindade.marvelapp.database

import android.annotation.SuppressLint
import android.util.Log
import io.github.maikotrindade.marvelapp.characters.domain.model.Character
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class CharacterRepository(var characterDao: CharacterDao) {

    fun getAllCharacterCharacter(): Flowable<List<Character>> {
        return characterDao.selectAll
    }

    fun insert(character: Character) {
        Observable.fromCallable { Runnable { characterDao.insert(character) }.run() }
            .subscribeOn(Schedulers.io())
            .subscribe({}, { Log.e(CharacterRepository::class.java.simpleName, it.localizedMessage) })
    }

    fun delete(character: Character) {
        Observable.fromCallable { Runnable { characterDao.delete(character) }.run() }
            .subscribeOn(Schedulers.io())
            .subscribe({}, { Log.e(CharacterRepository::class.java.simpleName, it.localizedMessage) })
    }

}
