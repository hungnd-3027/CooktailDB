package com.example.cooktaildb.ui.filter_fragment

import android.os.Bundle
import com.example.cooktaildb.base.BaseFragment
import com.example.cooktaildb.databinding.FragmentFilterBinding


class FilterFragment : BaseFragment<FragmentFilterBinding>(FragmentFilterBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FilterFragment()
    }
}
