package io.github.maikotrindade.marvelapp

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import io.github.maikotrindade.marvelapp.characters.ui.detail.DetailsCharacterActivity
import io.github.maikotrindade.marvelapp.characters.ui.detail.DetailsCharacterPresenter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class DetailsCharactersUnitTest {

    private lateinit var presenter: DetailsCharacterPresenter
    @Mock lateinit var activity: DetailsCharacterActivity
    private lateinit var characterId: String

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!
    @Rule @JvmField var testSchedulerRule = RxSchedulerRule()

    @Before
    fun setup() {
        activity = mock()
        presenter = DetailsCharacterPresenter(activity)
        characterId = "1009368"
    }

    @Test
    fun request_ComicsByCharacterSuccess() {
        presenter.requestComicsServer(characterId)
        verify(activity).onRequestComicsSuccess(anyList())
    }

    @Test
    fun requestComics_doesNotCallError() {
        presenter.requestComicsServer(characterId)
        verify(activity, never()).onRequestError(ArgumentMatchers.anyInt())
    }

    @Test
    fun request_SeriesByCharacterSuccess() {
        presenter.requestSeriesServer(characterId)
        verify(activity).onRequestSeriesSuccess(anyList())
    }

    @Test
    fun requestSeries_doesNotCallError() {
        presenter.requestSeriesServer(characterId)
        verify(activity, never()).onRequestError(ArgumentMatchers.anyInt())
    }

}
