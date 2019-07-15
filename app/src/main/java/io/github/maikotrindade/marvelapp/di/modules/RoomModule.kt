package io.github.maikotrindade.marvelapp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.maikotrindade.marvelapp.database.AppDatabase
import io.github.maikotrindade.marvelapp.database.CharacterDao
import io.github.maikotrindade.marvelapp.database.CharacterRepository
import io.github.maikotrindade.marvelapp.util.databaseName
import javax.inject.Singleton

@Module
class RoomModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideMyDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }

    @Singleton
    @Provides
    fun provideCharacterRepository(characterDao: CharacterDao): CharacterRepository {
        return CharacterRepository(characterDao)
    }
}
