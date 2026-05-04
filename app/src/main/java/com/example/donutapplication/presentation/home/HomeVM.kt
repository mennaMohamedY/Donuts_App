package com.example.donutapplication.presentation.home

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.donutapplication.presentation.common.SharedPrefCart
import com.example.donutapplication.presentation.dummy_data.CategoryDummyData
import com.example.donutapplication.presentation.dummy_data.donutsDummyDataList
import com.example.donutapplication.presentation.dummy_data.drinksDummyDataList
import com.example.donutapplication.presentation.dummy_data.icedDrinksDummyDataList
import com.example.donutapplication.presentation.details.DetailsActivity

class HomeVM(
    private val sharedPrefCart: SharedPrefCart
) : ViewModel() {


    private var _itemsInCart by mutableStateOf( sharedPrefCart.cartItems)
    val itemsCart = derivedStateOf { _itemsInCart }

    fun refreshItemsInCart(){
        _itemsInCart = sharedPrefCart.cartItems
    }



    private fun getDrinksData(): List<CategoryDummyData>{
        return drinksDummyDataList
    }
    private fun getIcedDrinksData(): List<CategoryDummyData>{
        return icedDrinksDummyDataList
    }
    private fun getDonutsData(): List<CategoryDummyData>{
        return donutsDummyDataList
    }

    fun getListFromID(id :Int): List<CategoryDummyData>{
       return when(id){
            0-> getDrinksData()
            1-> getIcedDrinksData()
            2-> getDonutsData()
           else -> getDrinksData()
        }
    }

    fun navigateToDetailsScreen(context: Context,categoryID:Int, itemID:Int){
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("categoryID",categoryID)
        intent.putExtra("itemID",itemID)
        context.startActivity(intent)
    }
}