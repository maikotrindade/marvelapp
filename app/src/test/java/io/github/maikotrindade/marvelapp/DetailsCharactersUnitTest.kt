package io.github.maikotrindade.marvelapp

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import io.github.maikotrindade.marvelapp.characters.view.detail.DetailsCharacterActivity
import io.github.maikotrindade.marvelapp.characters.view.detail.DetailsCharacterPresenter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.lang.ref.WeakReference

class DetailsCharactersUnitTest {

    private lateinit var presenter: DetailsCharacterPresenter
    @Mock lateinit var activity: DetailsCharacterActivity
    private lateinit var characterId: String

    @Rule @JvmField val rule = MockitoJUnit.rule()!!
    @Rule @JvmField var testSchedulerRule = RxSchedulerRule()

    @Before
    fun setup() {
        activity = mock()
        presenter = DetailsCharacterPresenter()
        presenter.view = WeakReference(activity)
        activity.presenter = presenter
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
        verify(activity, never()).onRequestError(anyInt())
    }

    @Test
    fun request_SeriesByCharacterSuccess() {
        presenter.requestSeriesServer(characterId)
        verify(activity).onRequestSeriesSuccess(anyList())
    }

    @Test
    fun requestSeries_doesNotCallError() {
        presenter.requestSeriesServer(characterId)
        verify(activity, never()).onRequestError(anyInt())
    }


}
