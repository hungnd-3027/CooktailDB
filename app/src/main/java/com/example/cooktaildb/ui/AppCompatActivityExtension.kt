package com.example.cooktaildb.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.beginTransaction().apply {
        add(frameId, fragment)
        addToBackStack(backStackTag)
        commit()
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.beginTransaction().apply {
        replace(frameId, fragment)
        addToBackStack(backStackTag)
        commit()
    }
}
