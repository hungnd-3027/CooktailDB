package com.example.cooktaildb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

typealias ActivityInflate<T> = (LayoutInflater) -> T
abstract class BaseActivity<B: ViewBinding>(val bindingFactory: ActivityInflate<B>) : AppCompatActivity() {
    private var _binding: B? = null
    val binding get() = _binding
    private var mProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding?.root)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun initData()
}
