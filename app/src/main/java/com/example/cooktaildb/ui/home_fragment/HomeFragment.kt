package com.example.cooktaildb.ui.home_fragment

import android.os.Bundle
import android.view.View
import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.data.model.Category
import com.example.cooktaildb.data.repository.CategoryRepository
import com.example.cooktaildb.data.source.remote.RemoteCategoryDataSource
import com.example.cooktaildb.databinding.FragmentHomeBinding
import com.example.cooktaildb.ui.home_fragment.adapter.DrinkViewPagerAdapter
import com.example.cooktaildb.ui.home_fragment.presenter.HomeFragmentContract
import com.example.cooktaildb.ui.home_fragment.presenter.HomeFragmentPresenter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), HomeFragmentContract.View {
    private var homePresenter: HomeFragmentPresenter? = null
    private var drinkAdapter = DrinkViewPagerAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homePresenter = HomeFragmentPresenter(CategoryRepository.getInstance(RemoteCategoryDataSource.getInstance()), this)

        homePresenter?.getCategory()

        binding?.apply {
            viewpager2Drink.adapter = drinkAdapter
        }
    }

    override fun getListCategorySuccess(categories: List<Category>) {
        drinkAdapter.setData(categories.toMutableList())

        binding?.apply {
            tabCategory.let {
                viewpager2Drink.let { viewpager ->
                    TabLayoutMediator(it, viewpager){ tab, position ->
                        tab.text = categories[position].strCategory
                    }.attach()
                }
            }
        }
    }

    override fun failed() {
//        TODO("Not yet implemented")
    }


    override fun showProgressBar() {
//        TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
//        TODO("Not yet implemented")
    }

    override fun showError() {
//        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
