package com.example.cooktaildb.ui.filter_fragment

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.cooktaildb.Constant
import com.example.cooktaildb.FilterType
import com.example.cooktaildb.R
import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.data.model.Alcoholic
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.model.Glass
import com.example.cooktaildb.data.repository.AlcoholicRepository
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.repository.GlassRepository
import com.example.cooktaildb.data.source.local.DatabaseHelper
import com.example.cooktaildb.data.source.local.LocalDrinkDataSource
import com.example.cooktaildb.data.source.local.dao.FavoriteDrinkDAOImpl
import com.example.cooktaildb.data.source.remote.RemoteAlcoholicDataSource
import com.example.cooktaildb.data.source.remote.RemoteCategoryDataSource
import com.example.cooktaildb.data.source.remote.RemoteDrinkDataSource
import com.example.cooktaildb.data.source.remote.RemoteGlassDataSource
import com.example.cooktaildb.databinding.DialogFilterByFirstLetterBinding
import com.example.cooktaildb.databinding.FragmentFilterBinding
import com.example.cooktaildb.ui.detail.DetailDrinkActivity
import com.example.cooktaildb.ui.home_fragment.adapter.DrinkAdapter

class FilterFragment : BaseFragment<FragmentFilterBinding>(FragmentFilterBinding::inflate),
    FilterFragmentContract.View,
    View.OnClickListener,
    DrinkAdapter.OnItemClickListener {

    private var presenter: FilterFragmentPresenter? = null
    private var categoryList = mutableListOf<String>()
    private var alcoholicList = mutableListOf<String>()
    private var glassList = mutableListOf<String>()
    private val drinks = mutableListOf<Drink>()
    private val drinkAdapter: DrinkAdapter? by lazy {
        context?.let { DrinkAdapter(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = FilterFragmentPresenter(
            CategoryRepository.getInstance(RemoteCategoryDataSource.getInstance()),
            AlcoholicRepository.getInstance(RemoteAlcoholicDataSource.getInstance()),
            GlassRepository.getInstance(RemoteGlassDataSource.getInstance()),
            DrinkRepository.getInstance(
                RemoteDrinkDataSource.getInstance(),
                LocalDrinkDataSource.getInstance(
                    FavoriteDrinkDAOImpl.getInstance(
                        DatabaseHelper.getInstance(context)
                    )
                )
            ),
            this
        )
        presenter?.apply {
            getAlcoholic()
            getCategory()
            getGlass()
        }

        drinkAdapter?.setOnItemClickListener(this)

        binding?.apply {
            recyclerDrinkFilter.adapter = drinkAdapter
            buttonFilterCategory.setOnClickListener(this@FilterFragment)
            buttonFilterAlcoholic.setOnClickListener(this@FilterFragment)
            buttonFilterGlass.setOnClickListener(this@FilterFragment)
            buttonFilterFirstLetter.setOnClickListener(this@FilterFragment)
        }
    }

    override fun getCategorySuccess(categories: List<Category>) {
        categories.forEach { it.strCategory?.let { categoryName -> categoryList.add(categoryName) } }
    }

    override fun getAlcoholicSuccess(alcoholicList: List<Alcoholic>) {
        alcoholicList.forEach { this.alcoholicList.add(it.strAlcoholic) }
    }

    override fun getGlassSuccess(glasses: List<Glass>) {
        glasses.forEach { glassList.add(it.strGlass) }
    }

    override fun getDrinkByCategorySuccess(drinks: List<Drink>) {
        setDrinks(drinks)
        drinkAdapter?.setData(drinks.toMutableList())
    }

    override fun getDrinkByAlcoholicSuccess(drinks: List<Drink>) {
        setDrinks(drinks)
        drinkAdapter?.setData(drinks.toMutableList())
    }

    override fun getDrinkByGlassSuccess(drinks: List<Drink>) {
        setDrinks(drinks)
        drinkAdapter?.setData(drinks.toMutableList())
    }

    override fun getDrinkByFirstLetterSuccess(drinks: List<Drink>) {
        setDrinks(drinks)
        drinkAdapter?.setData(drinks.toMutableList())
    }

    override fun insertDrinkSuccess() {
        Toast.makeText(context, R.string.msg_add_drink_to_favorite, Toast.LENGTH_SHORT).show()
    }

    override fun getDrinkByIDSuccess(drinks: List<Drink>) {
        presenter?.insertDrink(drinks.first())
    }

    override fun isFavorite(result: Boolean, position: Int) {
        drinks[position].isFavorite = result
        drinkAdapter?.setData(drinks)
    }

    override fun deleteDrinkSuccess() {
        Toast.makeText(context, R.string.msg_delete_drink_from_favorite, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        binding?.progressFilter?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding?.progressFilter?.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(context, R.string.msg_get_data_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(idDrink: String) {
        context?.let {
            DetailDrinkActivity.getIntent(it).also { intent ->
                val bundle = bundleOf(Constant.BUNDLE_ID_DRINK to idDrink)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    override fun onFavoriteClick(idDrink: String, isFavorite: Boolean, position: Int) {
        if (isFavorite) {
            presenter?.deleteDrink(idDrink)
        } else {
            presenter?.getDrinkByID(idDrink)
        }
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_filter_category -> {
                showDialog(categoryList.toTypedArray(), FilterType.CATEGORY_FILTER)
            }
            R.id.button_filter_alcoholic -> {
                showDialog(alcoholicList.toTypedArray(), FilterType.ALCOHOLIC_FILTER)
            }
            R.id.button_filter_glass -> {
                showDialog(glassList.toTypedArray(), FilterType.GLASS_FILTER)
            }
            R.id.button_filter_first_letter -> {
                showCustomDialog()
            }
        }
    }

    private fun showCustomDialog() {
        val dialogBinding = DialogFilterByFirstLetterBinding.inflate(layoutInflater)
        context?.let {
            Dialog(it).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(dialogBinding.root)
                setCancelable(false)
                window?.apply {
                    setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT
                    )
                    setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
                dialogBinding.apply {
                    buttonCancel.setOnClickListener { cancel() }
                    buttonFilterOke.setOnClickListener {
                        val letterFilter = dialogBinding.editTextFirstLetter.text.toString().trim()
                        binding?.textTitleFilter?.text =
                            resources.getString(R.string.title_filter_by_first_letter)
                                .plus(" $letterFilter")
                        if (letterFilter.length == 1) {
                            presenter?.getDrinkByFirstLetter(letterFilter)
                            dismiss()

                        } else {
                            Toast.makeText(context, R.string.msg_enter_letter, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                show()
            }
        }
    }

    private fun showDialog(listItem: Array<String>, filterType: Int) {
        AlertDialog.Builder(context).apply {
            setTitle(getString(R.string.title_filter_dialog))
            setItems(listItem) { _, which ->
                when (filterType) {
                    FilterType.CATEGORY_FILTER -> {
                        binding?.textTitleFilter?.text =
                            resources.getString(R.string.title_filter_by_category)
                                .plus(" " + listItem[which])
                        presenter?.getDrinkByCategory(listItem[which])
                    }
                    FilterType.ALCOHOLIC_FILTER -> {
                        binding?.textTitleFilter?.text =
                            resources.getString(R.string.title_filter_by_alcoholic)
                                .plus(" " + listItem[which])
                        presenter?.getDrinkByAlcoholic(listItem[which])
                    }
                    FilterType.GLASS_FILTER -> {
                        binding?.textTitleFilter?.text =
                            resources.getString(R.string.title_filter_by_glass)
                                .plus(" " + listItem[which])
                        presenter?.getDrinkByGlass(listItem[which])
                    }
                }
            }
            show()
        }
    }

    private fun setDrinks(drinks: List<Drink>) {
        this.drinks.clear()
        this.drinks.addAll(drinks)
        checkFavorite(drinks)
    }

    private fun checkFavorite(drinks: List<Drink>) {
        drinks.forEachIndexed { index, drink ->
            drink.idDrink?.let { presenter?.isFavorite(it, index) }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FilterFragment()
    }
}
