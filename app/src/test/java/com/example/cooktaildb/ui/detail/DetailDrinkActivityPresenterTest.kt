package com.example.cooktaildb.ui.detail

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class
DetailDrinkActivityPresenterTest {
    private val view = mockk<DetailDrinkActivityContract.View>(relaxed = true)
    private val repository = mockk<DrinkRepository>()
    private val detailDrinkPresenter = DetailDrinkActivityPresenter(repository, view)
    private val callback = slot<OnRequestCallback<List<Drink>>>()
    private val localCallback = slot<OnRequestLocalCallback<Unit>>()
    private val localCallbackBoolean = slot<OnRequestLocalCallback<Boolean>>()

    @Test
    fun `getDrinkDetail callback return onSuccess`() {
        val drink = Drink("11007")
        val drinks = listOf<Drink>(drink)
        every {
            repository.getDrinkByID("11007", capture(callback))
        } answers {
            callback.captured.onSuccess(drinks)
        }
        detailDrinkPresenter.getDrinkByID("11007")
        verify {
            view.getDrinkByIDSuccess(drinks)
        }
    }

    @Test
    fun `getDrinkDetail callback return onFailure`() {
        every {
            repository.getDrinkByID("12132313", capture(callback))
        } answers {
            callback.captured.onFailed()
        }
        detailDrinkPresenter.getDrinkByID("12132313")
        verify {
            view.showError()
        }
    }

    @Test
    fun `getRandomDrink callback return onSuccess`() {
        val drinks = listOf(Drink())
        every {
            repository.getRandomDrink(capture(callback))
        } answers {
            callback.captured.onSuccess(drinks)
        }
        detailDrinkPresenter.getRandomDrink()
        verify {
            view.getRandomDrinkSuccess(drinks)
        }
    }

    @Test
    fun `getRandomDrink callback return onFailure`() {
        every {
            repository.getRandomDrink(capture(callback))
        } answers {
            callback.captured.onFailed()
        }
        detailDrinkPresenter.getRandomDrink()
        verify {
            view.showError()
        }
    }

    @Test
    fun `insertDrink callback return onSuccess`() {
        val drink = Drink("11007")
        every {
            repository.insertDrink(drink, capture(localCallback))
        } answers {
            localCallback.captured.onSuccess(Unit)
        }
        every {
            drink.idDrink?.let { it1 -> repository.isFavorite(it1, capture(localCallbackBoolean)) }
        } answers {
            localCallbackBoolean.captured.onSuccess(data = true)
        }
        detailDrinkPresenter.insertDrink(drink)
        verify {
            view.insertDrinkSuccess()
        }
    }

    @Test
    fun `insertDrink callback return onFailure`() {
        val drink = Drink("11007")
        every {
            repository.insertDrink(drink, capture(localCallback))
        } answers {
            localCallback.captured.onFailed()
        }
        detailDrinkPresenter.insertDrink(drink)
        verify {
            view.showError()
        }
    }

    @Test
    fun `deleteDrink callback return onSuccess`() {
        val idDrink = "11007"
        every {
            repository.deleteDrink(idDrink, capture(localCallback))
        } answers {
            localCallback.captured.onSuccess(Unit)
        }
        every {
            repository.isFavorite(idDrink, capture(localCallbackBoolean))
        } answers {
            localCallbackBoolean.captured.onSuccess(data = true)
        }
        detailDrinkPresenter.deleteDrink(idDrink)
        verify {
            view.deleteDrinkSuccess()
        }
    }

    @Test
    fun `deleteDrink callback return onFailure`() {
        val idDrink = "11007"
        every {
            repository.deleteDrink(idDrink, capture(localCallback))
        } answers {
            localCallback.captured.onFailed()
        }
        detailDrinkPresenter.deleteDrink(idDrink)
        verify {
            view.showError()
        }
    }

    @Test
    fun `isFavoriteDrink callback return onSuccess`() {
        val idDrink = "11007"
        every {
            repository.isFavorite(idDrink, capture(localCallbackBoolean))
        } answers {
            localCallbackBoolean.captured.onSuccess(data = true)
        }
        detailDrinkPresenter.isFavorite(idDrink)
        verify {
            view.isFavorite(result = true)
        }
    }

    @Test
    fun `isFavoriteDrink callback return onFailure`() {
        val idDrink = "11007"
        every {
            repository.isFavorite(idDrink, capture(localCallbackBoolean))
        } answers {
            localCallbackBoolean.captured.onFailed()
        }
        detailDrinkPresenter.isFavorite(idDrink)
        verify {
            view.showError()
        }
    }
}
