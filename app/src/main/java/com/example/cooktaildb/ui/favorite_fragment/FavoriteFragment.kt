package com.example.cooktaildb.ui.favorite_fragment

import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}
