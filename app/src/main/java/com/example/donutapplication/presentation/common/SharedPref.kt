package com.example.donutapplication.presentation.common

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPrefCart(context: Context) {

    companion object {
        private const val PREFS_NAME = "SHARED_PREF_CART"
        private const val ITEMS_IN_CART = "ITEMS_IN_CART"

    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var cartItems: Int
        get() = sharedPreferences.getInt(ITEMS_IN_CART, 0)
        set(value) = sharedPreferences.edit {
            putInt(ITEMS_IN_CART, value)
        }
}
