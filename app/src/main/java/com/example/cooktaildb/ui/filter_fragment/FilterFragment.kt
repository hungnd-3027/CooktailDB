package com.example.cooktaildb.ui.filter_fragment

import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.databinding.FragmentFilterBinding

class FilterFragment : BaseFragment<FragmentFilterBinding>(FragmentFilterBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = FilterFragment()
    }
}
