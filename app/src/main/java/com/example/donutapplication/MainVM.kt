package com.example.donutapplication

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainVM  : ViewModel(){

    private var _itemsInCart by mutableIntStateOf(0)
    val itemsInCart = derivedStateOf { _itemsInCart }

    fun addToCart(){
        _itemsInCart +=1
    }


}