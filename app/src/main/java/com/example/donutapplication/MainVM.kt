package com.example.donutapplication

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainVM  : ViewModel(){

    private var _itemsInCart by mutableIntStateOf(0)

    fun addToCart(){
        _itemsInCart +=1
        getCartItems()
        Log.e("addToCart","additem to cart ${_itemsInCart}")
    }

    fun getCartItems(): Int{
        return _itemsInCart
    }


}