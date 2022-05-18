package com.example.cooktaildb.ui.favorite_fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cooktaildb.R
import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.data.model.Drink
import com.example.cooktaildb.data.repository.DrinkRepository
import com.example.cooktaildb.data.source.local.DatabaseHelper
import com.example.cooktaildb.data.source.local.LocalDrinkDataSource
import com.example.cooktaildb.data.source.local.dao.FavoriteDrinkDAOImpl
import com.example.cooktaildb.data.source.remote.RemoteDrinkDataSource
import com.example.cooktaildb.databinding.FragmentFavoriteBinding
import com.example.cooktaildb.ui.favorite_fragment.adapter.FavoriteAdapter
import com.example.cooktaildb.ui.favorite_fragment.presenter.FavoriteFragmentContract
import com.example.cooktaildb.ui.favorite_fragment.presenter.FavoriteFragmentPresenter

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate),
    FavoriteFragmentContract.View {

    private var presenter: FavoriteFragmentPresenter? = null
    private val adapter: FavoriteAdapter? by lazy {
        context?.let { FavoriteAdapter(it) }
    }
    private val listDrink = mutableListOf<Drink>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initData()
        swipeItemToDelete()
    }

    override fun getAllDrinkSuccess(data: List<Drink>) {
        listDrink.clear()
        listDrink.addAll(data)
        adapter?.setData(data)
    }

    override fun deleteDrinkSuccess() {
        Toast.makeText(context, R.string.msg_delete_drink_from_favorite, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        binding?.progressFavorite?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding?.progressFavorite?.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(context, R.string.msg_get_data_failed, Toast.LENGTH_SHORT).show()
    }

    private fun swipeItemToDelete() {
        val swipeToDeleteCallback = object : SwipeItemCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                listDrink[position].idDrink?.let { presenter?.deleteDrink(it) }
                listDrink.removeAt(position)
                adapter?.setData(listDrink)
            }
        }
        ItemTouchHelper(swipeToDeleteCallback).apply {
            attachToRecyclerView(binding?.recyclerFavorite)
        }
    }

    private fun initData() {
        presenter = FavoriteFragmentPresenter(
            DrinkRepository.getInstance(
                RemoteDrinkDataSource.getInstance(),
                LocalDrinkDataSource.getInstance(
                    FavoriteDrinkDAOImpl.getInstance(DatabaseHelper.getInstance(context))
                )
            ), this
        )
        presenter?.getAllDrink()
    }

    private fun initView() {
        binding?.apply {
            recyclerFavorite.adapter = adapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}
