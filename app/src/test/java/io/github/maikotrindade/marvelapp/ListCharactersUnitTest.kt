package io.github.maikotrindade.marvelapp

import io.github.maikotrindade.marvelapp.characters.domain.api.CharactersService
import io.github.maikotrindade.marvelapp.util.EncryptUtil
import io.github.maikotrindade.marvelapp.util.basePagination
import io.github.maikotrindade.marvelapp.util.publicKey
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

class ListCharactersUnitTest {

    @Rule @JvmField val rule = MockitoJUnit.rule()!!
    @Rule @JvmField var testSchedulerRule = RxSchedulerRule()

    @Test
    fun fetchData_Characters_NotNull_NotEmpty() {

        val timestamp = EncryptUtil.getEpochTime()
        val hash = EncryptUtil.getHash(timestamp)

        CharactersService.requestCharacters(timestamp, publicKey, hash!!, 0)
            .subscribe({ res ->
                res.body()?.let {
                    val responseList = it.data.results
                    Assert.assertNotNull(responseList)
                    Assert.assertNotEquals(responseList.size, 0)
                }
            }) { }
    }

    @Test
    fun fetchData_Characters_RequestLimit_Success() {
        val timestamp = EncryptUtil.getEpochTime()
        val hash = EncryptUtil.getHash(timestamp)
        CharactersService.requestCharacters(timestamp, publicKey, hash!!, 0)
            .subscribe({ res ->
                res.body()?.let {
                    val results = it.data.results
                    Assert.assertEquals(results.size, basePagination)
                }
            }) { }
    }


}
