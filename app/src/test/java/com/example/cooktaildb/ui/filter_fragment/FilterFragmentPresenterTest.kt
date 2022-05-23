package com.example.cooktaildb.ui.filter_fragment

import com.example.cooktaildb.data.model.Alcoholic
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.model.Glass
import com.example.cooktaildb.data.repository.AlcoholicRepository
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.repository.GlassRepository
import com.example.cooktaildb.data.source.local.utils.OnRequestLocalCallback
import com.example.cooktaildb.data.source.remote.OnRequestCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test

class FilterFragmentPresenterTest {
    private val view = mockk<FilterFragmentContract.View>(relaxed = true)
    private val drinkRepository = mockk<DrinkRepository>()
    private val categoryRepository = mockk<CategoryRepository>()
    private val alcoholicRepository = mockk<AlcoholicRepository>()
    private val glassRepository = mockk<GlassRepository>()
    private val filterPresenter = FilterFragmentPresenter(
        categoryRepository,
        alcoholicRepository,
        glassRepository,
        drinkRepository,
        view
    )
    private val remoteCallbackCategory = slot<OnRequestCallback<List<Category>>>()
    private val remoteCallbackAlcoholic = slot<OnRequestCallback<List<Alcoholic>>>()
    private val remoteCallbackGlass = slot<OnRequestCallback<List<Glass>>>()
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
        filterPresenter.getCategory()
        verify {
            view.getCategorySuccess(categories)
        }
    }

    @Test
    fun `getAlcoholicList callback return onSuccess`() {
        val alcoholic = Alcoholic()
        val alcoholicList = listOf(alcoholic)
        every {
            alcoholicRepository.getAlcoholic(capture(remoteCallbackAlcoholic))
        } answers {
            remoteCallbackAlcoholic.captured.onSuccess(alcoholicList)
        }
        filterPresenter.getAlcoholic()
        verify {
            view.getAlcoholicSuccess(alcoholicList)
        }
    }

    @Test
    fun `getGlasses callback return onSuccess`() {
        val glass = Glass()
        val glasses = listOf(glass)
        every {
            glassRepository.getGlass(capture(remoteCallbackGlass))
        } answers {
            remoteCallbackGlass.captured.onSuccess(glasses)
        }
        filterPresenter.getGlass()
        verify {
            view.getGlassSuccess(glasses)
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
        filterPresenter.getDrinkByCategory(strCategory)
        verify {
            view.getDrinkByCategorySuccess(drinks)
        }
    }

    @Test
    fun `getDrinkByAlcoholic callback return onSuccess`() {
        val drinks = listOf(Drink())
        val strAlcoholic = "Alcoholic"
        every {
            drinkRepository.getDrinkByAlcoholic(strAlcoholic, capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onSuccess(drinks)
        }
        filterPresenter.getDrinkByAlcoholic(strAlcoholic)
        verify {
            view.getDrinkByAlcoholicSuccess(drinks)
        }
    }

    @Test
    fun `getDrinkByGlass callback return onSuccess`() {
        val drinks = listOf(Drink())
        val strGlass = "Highball glass"
        every {
            drinkRepository.getDrinkByGlass(strGlass, capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onSuccess(drinks)
        }
        filterPresenter.getDrinkByGlass(strGlass)
        verify {
            view.getDrinkByGlassSuccess(drinks)
        }
    }

    @Test
    fun `getDrinkByFirstLetter callback return onSuccess`() {
        val drinks = listOf(Drink())
        val firstLetter = "a"
        every {
            drinkRepository.getDrinkByFirstLetter(firstLetter, capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onSuccess(drinks)
        }
        filterPresenter.getDrinkByFirstLetter(firstLetter)
        verify {
            view.getDrinkByFirstLetterSuccess(drinks)
        }
    }

    @Test
    fun `getCategory callback return onFailure`() {
        every {
            categoryRepository.getCategories(capture(remoteCallbackCategory))
        } answers {
            remoteCallbackCategory.captured.onFailed()
        }
        filterPresenter.getCategory()
        verify {
            view.showError()
        }
    }

    @Test
    fun `getAlcoholicList callback return onFailure`() {
        every {
            alcoholicRepository.getAlcoholic(capture(remoteCallbackAlcoholic))
        } answers {
            remoteCallbackAlcoholic.captured.onFailed()
        }
        filterPresenter.getAlcoholic()
        verify {
            view.showError()
        }
    }

    @Test
    fun `getGlasses callback return onFailure`() {
        every {
            glassRepository.getGlass(capture(remoteCallbackGlass))
        } answers {
            remoteCallbackGlass.captured.onFailed()
        }
        filterPresenter.getGlass()
        verify {
            view.showError()
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
        filterPresenter.getDrinkByCategory(strCategory)
        verify {
            view.showError()
        }
    }

    @Test
    fun `getDrinkByAlcoholic callback return onFailure`() {
        val strAlcoholic = "Alcoholic"
        every {
            drinkRepository.getDrinkByAlcoholic(strAlcoholic, capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onFailed()
        }
        filterPresenter.getDrinkByAlcoholic(strAlcoholic)
        verify {
            view.showError()
        }
    }

    @Test
    fun `getDrinkByGlass callback return onFailure`() {
        val strGlass = "Highball glass"
        every {
            drinkRepository.getDrinkByGlass(strGlass, capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onFailed()
        }
        filterPresenter.getDrinkByGlass(strGlass)
        verify {
            view.showError()
        }
    }

    @Test
    fun `getDrinkByFirstLetter callback return onFailure`() {
        val firstLetter = "a"
        every {
            drinkRepository.getDrinkByFirstLetter(firstLetter, capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onFailed()
        }
        filterPresenter.getDrinkByFirstLetter(firstLetter)
        verify {
            view.showError()
        }
    }

    @Test
    fun `getDrinkByID callback return onSuccess`() {
        val drink = Drink("11007")
        val drinks = listOf<Drink>(drink)
        every {
            drinkRepository.getDrinkByID("11007", capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onSuccess(drinks)
        }
        filterPresenter.getDrinkByID("11007")
        verify {
            view.getDrinkByIDSuccess(drinks)
        }
    }

    @Test
    fun `getDrinkByID callback return onFailure`() {
        every {
            drinkRepository.getDrinkByID("", capture(remoteCallbackDrink))
        } answers {
            remoteCallbackDrink.captured.onFailed()
        }
        filterPresenter.getDrinkByID("")
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
        filterPresenter.insertDrink(drink)
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
        filterPresenter.insertDrink(drink)
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
        filterPresenter.deleteDrink(idDrink)
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
        filterPresenter.deleteDrink(idDrink)
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
        filterPresenter.isFavorite(idDrink, position)
        verify {
            view.isFavorite(result = true, position)
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
        filterPresenter.isFavorite(idDrink, position)
        verify {
            view.showError()
        }
    }
}
