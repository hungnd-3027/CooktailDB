package com.example.cooktaildb.ui.home_fragment.presenter

import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class HomeFragmentPresenterTest {
    private val view = mockk<HomeFragmentContract.View>(relaxed = true)
    private val drinkRepository = mockk<DrinkRepository>()
    private val categoryRepository = mockk<CategoryRepository>()

    private val homePresenter = HomeFragmentPresenter(
        categoryRepository,
        drinkRepository,
        view
    )
    private val remoteCallbackCategory = slot<OnRequestCallback<List<Category>>>()
    private val remoteCallbackDrink = slot<OnRequestCallback<List<Drink>>>()
    private val localCallbackUnit = slot<OnRequestLocalCallback<Unit>>()
    private val localCallbackBoolean = slot<OnRequestLocalCallback<Boolean>>()

    @Test
    fun `getCategory callback return onSuccess`() {
        val category = Category()
        val categories = listOf(category)
        every {
            categoryRepository.getCategories(capture(remoteCallbackCategory))
        } answers {
            remoteCallbackCategory.captured.onSuccess(categories)
        }
        homePresenter.getCategory()
        verify {
            view.getListCategorySuccess(categories)
        }
    }

    @Test
    fun `getCategory callback return onFailure`() {
        every {
            categoryRepository.getCategories(capture(remoteCallbackCategory))
        } answers {
            remoteCallbackCategory.captured.onFailed()
        }
        homePresenter.getCategory()
        verify {
            view.failed()
        }
    }

    @Test
    fun `getDrinkByCategory callback return onSuccess`() {
        val drinks = listOf(Drink())
        val strCategory = "Ordinary Drink"
        every {
            drinkRepository.getDrinks(strCategory, capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onSuccess(drinks)
        }
        homePresenter.getDrinkBySelectedCategory(strCategory)
        verify {
            view.getDrinkBySelectedCategorySuccess(drinks)
        }
    }

    @Test
    fun `getDrinkByCategory callback return onFailure`() {
        val strCategory = "Ordinary Drink"
        every {
            drinkRepository.getDrinks(strCategory, capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onFailed()
        }
        homePresenter.getDrinkBySelectedCategory(strCategory)
        verify {
            view.failed()
        }
    }

    @Test
    fun `getDrinkDetail callback return onSuccess`() {
        val drink = Drink("11007")
        val drinks = listOf<Drink>(drink)
        every {
            drinkRepository.getDrinkByID("11007", capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onSuccess(drinks)
        }
        homePresenter.getDetailDrink("11007")
        verify {
            view.getDetailDrinkSuccess(drinks)
        }
    }

    @Test
    fun `getDrinkDetail callback return onFailure`() {
        every {
            drinkRepository.getDrinkByID("", capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onFailed()
        }
        homePresenter.getDetailDrink("")
        verify {
            view.showError()
        }
    }

    @Test
    fun `insertDrink callback return onSuccess`() {
        val drink = Drink("11007")
        every {
            drinkRepository.insertDrink(drink, capture(localCallbackUnit))
        } answers {
            localCallbackUnit.captured.onSuccess(Unit)
        }
        homePresenter.insertDrink(drink)
        verify {
            view.insertDrinkSuccess()
        }
    }

    @Test
    fun `insertDrink callback return onFailure`() {
        val drink = Drink("11007")
        every {
            drinkRepository.insertDrink(drink, capture(localCallbackUnit))
        } answers {
            localCallbackUnit.captured.onFailed()
        }
        homePresenter.insertDrink(drink)
        verify {
            view.showError()
        }
    }

    @Test
    fun `deleteDrink callback return onSuccess`() {
        val idDrink = "11007"
        every {
            drinkRepository.deleteDrink(idDrink, capture(localCallbackUnit))
        } answers {
            localCallbackUnit.captured.onSuccess(Unit)
        }
        homePresenter.deleteDrink(idDrink)
        verify {
            view.deleteDrinkSuccess()
        }
    }

    @Test
    fun `deleteDrink callback return onFailure`() {
        val idDrink = "11007"
        every {
            drinkRepository.deleteDrink(idDrink, capture(localCallbackUnit))
        } answers {
            localCallbackUnit.captured.onFailed()
        }
        homePresenter.deleteDrink(idDrink)
        verify {
            view.showError()
        }
    }

    @Test
    fun `isFavoriteDrink callback return onSuccess`() {
        val idDrink = "11007"
        val position = 1
        every {
            drinkRepository.isFavorite(idDrink, capture(localCallbackBoolean))
        } answers {
            localCallbackBoolean.captured.onSuccess(data = true)
        }
        homePresenter.isFavorite(idDrink, position)
        verify {
            view.checkFavoriteSuccess(result = true, position)
        }
    }

    @Test
    fun `isFavoriteDrink callback return onFailure`() {
        val idDrink = "11007"
        val position = 1
        every {
            drinkRepository.isFavorite(idDrink, capture(localCallbackBoolean))
        } answers {
            localCallbackBoolean.captured.onFailed()
        }
        homePresenter.isFavorite(idDrink, position)
        verify {
            view.showError()
        }
    }
}
