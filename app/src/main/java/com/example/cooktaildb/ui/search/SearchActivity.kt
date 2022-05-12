package com.example.cooktaildb.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cooktaildb.Constant
import com.example.cooktaildb.R
import com.example.cooktaildb.base.BaseActivity
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.remote.RemoteDrinkDataSource
import com.example.cooktaildb.databinding.ActivitySearchBinding
import com.example.cooktaildb.ui.detail.DetailDrinkActivity
import com.example.cooktaildb.ui.home_fragment.adapter.DrinkAdapter

class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate),
    SearchActivityContract.View,
    DrinkAdapter.OnItemClickListener {

    private var searchPresenter: SearchActivityPresenter? = null
    private val drinkAdapter: DrinkAdapter by lazy {
        DrinkAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchPresenter = SearchActivityPresenter(
            DrinkRepository.getInstance(RemoteDrinkDataSource.getInstance()),
            this
        )

        drinkAdapter.setOnItemClickListener(this)

        binding?.apply {
            recyclerDrinkSearch.layoutManager = GridLayoutManager(this@SearchActivity, 2)
            recyclerDrinkSearch.adapter = drinkAdapter
            editTextSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    drinkAdapter.setData(mutableListOf())
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0.toString().trim().apply {
                        if (isNotEmpty()) {
                            searchPresenter?.searchDrink(this)
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    drinkAdapter.setData(mutableListOf())
                }
            })
        }
    }

    override fun failed() {
        Toast.makeText(this, R.string.msg_get_data_failed, Toast.LENGTH_SHORT).show()
    }

    override fun getDrinkSuccess(drinks: List<Drink>) {
        binding?.textGetDataFailed?.visibility = if (drinks.isEmpty()) View.VISIBLE else View.GONE
        drinkAdapter.setData(drinks.toMutableList())
    }

    override fun showProgressBar() {
        binding?.progressSearch?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding?.progressSearch?.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(this, R.string.msg_get_data_failed, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(idDrink: String) {
        DetailDrinkActivity.getIntent(this).also { intent ->
            val bundle = bundleOf(Constant.BUNDLE_ID_DRINK to idDrink)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    companion object {
        fun getIntent(startActivity: Context) = Intent(startActivity, SearchActivity::class.java)
    }
}
