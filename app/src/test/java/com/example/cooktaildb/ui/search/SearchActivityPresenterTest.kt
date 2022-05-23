package com.example.cooktaildb.ui.search

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class SearchActivityPresenterTest {
    private val view = mockk<SearchActivityContract.View>(relaxed = true)
    private val repository = mockk<DrinkRepository>()
    private val searchDrinkPresenter = SearchActivityPresenter(repository, view)
    private val remoteCallback = slot<OnRequestCallback<List<Drink>>>()
    private val localCallbackUnit = slot<OnRequestLocalCallback<Unit>>()
    private val localCallbackBoolean = slot<OnRequestLocalCallback<Boolean>>()

    @Test
    fun `searchDrink callback return onSuccess`() {
        val strSearch = "abc"
        val drinks = listOf(Drink())
        every {
            repository.searchDrink(strSearch, capture(remoteCallback))
        } answers {
            remoteCallback.captured.onSuccess(drinks)
        }
        searchDrinkPresenter.searchDrink(strSearch)
        verify {
            view.getDrinkSuccess(drinks)
        }
    }

    @Test
    fun `searchDrink callback return onFailure`() {
        val strSearch = "abc"
        every {
            repository.searchDrink(strSearch, capture(remoteCallback))
        } answers {
            remoteCallback.captured.onFailed()
        }
        searchDrinkPresenter.searchDrink(strSearch)
        verify {
            view.failed()
        }
    }

    @Test
    fun `getDrinkByID callback return onSuccess`() {
        val drink = Drink("11007")
        val drinks = listOf(drink)
        every {
            repository.getDrinkByID("11007", capture(remoteCallback))
        } answers {
            remoteCallback.captured.onSuccess(drinks)
        }
        searchDrinkPresenter.getDrinkByID("11007")
        verify {
            view.getDrinkByIDSuccess(drinks)
        }
    }

    @Test
    fun `getDrinkByID callback return onFailure`() {
        every {
            repository.getDrinkByID("", capture(remoteCallback))
        } answers {
            remoteCallback.captured.onFailed()
        }
        searchDrinkPresenter.getDrinkByID("")
        verify {
            view.showError()
        }
    }

    @Test
    fun `insertDrink callback return onSuccess`() {
        val drink = Drink("11007")
        every {
            repository.insertDrink(drink, capture(localCallbackUnit))
        } answers {
            localCallbackUnit.captured.onSuccess(Unit)
        }
        searchDrinkPresenter.insertDrink(drink)
        verify {
            view.insertDrinkSuccess()
        }
    }

    @Test
    fun `insertDrink callback return onFailure`() {
        val drink = Drink("11007")
        every {
            repository.insertDrink(drink, capture(localCallbackUnit))
        } answers {
            localCallbackUnit.captured.onFailed()
        }
        searchDrinkPresenter.insertDrink(drink)
        verify {
            view.showError()
        }
    }

    @Test
    fun `deleteDrink callback return onSuccess`() {
        val idDrink = "11007"
        every {
            repository.deleteDrink(idDrink, capture(localCallbackUnit))
        } answers {
            localCallbackUnit.captured.onSuccess(Unit)
        }
        searchDrinkPresenter.deleteDrink(idDrink)
        verify {
            view.deleteDrinkSuccess()
        }
    }

    @Test
    fun `deleteDrink callback return onFailure`() {
        val idDrink = "11007"
        every {
            repository.deleteDrink(idDrink, capture(localCallbackUnit))
        } answers {
            localCallbackUnit.captured.onFailed()
        }
        searchDrinkPresenter.deleteDrink(idDrink)
        verify {
            view.showError()
        }
    }

    @Test
    fun `isFavoriteDrink callback return onSuccess`() {
        val idDrink = "11007"
        val position = 1
        every {
            repository.isFavorite(idDrink, capture(localCallbackBoolean))
        } answers {
            localCallbackBoolean.captured.onSuccess(data = true)
        }
        searchDrinkPresenter.isFavorite(idDrink, position)
        verify {
            view.isFavorite(result = true, position)
        }
    }

    @Test
    fun `isFavoriteDrink callback return onFailure`() {
        val idDrink = "11007"
        val position = 1
        every {
            repository.isFavorite(idDrink, capture(localCallbackBoolean))
        } answers {
            localCallbackBoolean.captured.onFailed()
        }
        searchDrinkPresenter.isFavorite(idDrink, position)
        verify {
            view.showError()
        }
    }
}
