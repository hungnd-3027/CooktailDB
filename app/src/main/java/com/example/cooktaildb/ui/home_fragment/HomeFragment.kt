package com.example.cooktaildb.ui.home_fragment

import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
