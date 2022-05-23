package com.example.cooktaildb.ui.favorite_fragment.presenter

import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class FavoriteFragmentPresenterTest {
    private val view = mockk<FavoriteFragmentContract.View>(relaxed = true)
    private val repository = mockk<DrinkRepository>()
    private val favoritePresenter = FavoriteFragmentPresenter(repository, view)
    private val localCallback = slot<OnRequestLocalCallback<List<Drink>>>()
    private val localCallbackUnit = slot<OnRequestLocalCallback<Unit>>()

    @Test
    fun `getAllDrink callback return onSuccess`() {
        val drink = Drink("11007")
        val drinks = listOf(drink)
        every {
            repository.getAllDrink(capture(localCallback))
        } answers {
            localCallback.captured.onSuccess(drinks)
        }
        favoritePresenter.getAllDrink()
        verify {
            view.getAllDrinkSuccess(drinks)
        }
    }

    @Test
    fun `getAllDrink callback return onFailure`() {
        every {
            repository.getAllDrink(capture(localCallback))
        } answers {
            localCallback.captured.onFailed()
        }
        favoritePresenter.getAllDrink()
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
        favoritePresenter.deleteDrink(idDrink)
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
        favoritePresenter.deleteDrink(idDrink)
        verify {
            view.showError()
        }
    }
}
