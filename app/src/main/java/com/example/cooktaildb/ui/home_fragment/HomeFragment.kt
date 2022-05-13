package com.example.cooktaildb.ui.home_fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.cooktaildb.Constant
import com.example.cooktaildb.R
import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.remote.RemoteCategoryDataSource
import com.example.cooktaildb.data.source.remote.RemoteDrinkDataSource
import com.example.cooktaildb.databinding.FragmentHomeBinding
import com.example.cooktaildb.ui.detail.DetailDrinkActivity
import com.example.cooktaildb.ui.home_fragment.adapter.DrinkAdapter
import com.example.cooktaildb.ui.home_fragment.adapter.DrinkViewPagerAdapter
import com.example.cooktaildb.ui.home_fragment.presenter.HomeFragmentContract
import com.example.cooktaildb.ui.home_fragment.presenter.HomeFragmentPresenter
import com.example.cooktaildb.ui.search.SearchActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeFragmentContract.View, TabLayout.OnTabSelectedListener,
    View.OnClickListener, DrinkViewPagerAdapter.OnItemClickListener
{
    private var homePresenter: HomeFragmentPresenter? = null
    private val categoryAdapter: DrinkViewPagerAdapter? by lazy {
        context?.let { DrinkViewPagerAdapter(it) }
    }
    private var listCategory = mutableListOf<Category>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homePresenter = HomeFragmentPresenter(
            CategoryRepository.getInstance(RemoteCategoryDataSource.getInstance()),
            DrinkRepository.getInstance(RemoteDrinkDataSource.getInstance()),
            this)

        homePresenter?.getCategory()

        categoryAdapter?.setOnItemClickListener(this)
        binding?.apply {
            viewpager2Drink.adapter = categoryAdapter
            tabCategory.addOnTabSelectedListener(this@HomeFragment)
            editTextSearch.setOnClickListener(this@HomeFragment)
            buttonGetRandom.setOnClickListener(this@HomeFragment)
        }
    }

    override fun getListCategorySuccess(categories: List<Category>) {
        listCategory = categories.toMutableList()
        categoryAdapter?.setData(categories.toMutableList())
        binding?.apply {
            tabCategory.let {
                viewpager2Drink.let { viewpager ->
                    TabLayoutMediator(it, viewpager) { tab, position ->
                        tab.text = categories[position].strCategory
                    }.attach()
                }
            }
        }
    }

    override fun failed() {
        Toast.makeText(context, R.string.msg_get_data_failed, Toast.LENGTH_SHORT).show()
    }

    override fun getDrinkBySelectedCategorySuccess(drinks: List<Drink>) {
        categoryAdapter?.setDrinkData(drinks.toMutableList())
    }

    override fun showProgressBar() {
        binding?.progressHome?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding?.progressHome?.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(context, R.string.msg_get_data_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab != null) {
            getDrinkBySelectedTab(listCategory[tab.position].strCategory)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.edit_text_search -> {
                context?.let {
                    SearchActivity.getIntent(it).also { intent ->
                        startActivity(intent)
                    }
                }
            }
            R.id.button_get_random -> {
                context?.let {
                    DetailDrinkActivity.getIntent(it).also { intent ->
                        startActivity(intent)
                    }
                }
            }
        }
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

    private fun getDrinkBySelectedTab(strCategory: String?) {
        strCategory?.let { homePresenter?.getDrinkBySelectedCategory(it) }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
