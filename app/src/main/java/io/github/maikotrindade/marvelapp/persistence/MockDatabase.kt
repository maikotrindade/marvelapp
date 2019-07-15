package io.github.maikotrindade.marvelapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.maikotrindade.marvelapp.characters.domain.model.Character

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class MockDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {

        @Volatile
        private var INSTANCE: MockDatabase? = null

        fun getInstance(context: Context): MockDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.inMemoryDatabaseBuilder(context.applicationContext, MockDatabase::class.java)
                .allowMainThreadQueries()
                .build()

        fun closeDatabase() {
            INSTANCE = null
        }
    }
}
