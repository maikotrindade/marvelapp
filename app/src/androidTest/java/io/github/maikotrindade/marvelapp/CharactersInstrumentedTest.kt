package io.github.maikotrindade.marvelapp

import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.github.maikotrindade.marvelapp.base.BaseApp
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CharactersInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = ApplicationProvider.getApplicationContext<BaseApp>()
        assertEquals("io.github.maikotrindade.marvelapp", appContext.packageName)
    }
}
