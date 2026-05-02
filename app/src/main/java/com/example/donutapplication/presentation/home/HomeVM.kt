package com.example.donutapplication.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.donutapplication.R
import com.example.donutapplication.data.CategoryDummyData
import com.example.donutapplication.data.donutsDummyDataList
import com.example.donutapplication.data.drinksDummyDataList
import com.example.donutapplication.data.icedDrinksDummyDataList

class HomeVM : ViewModel() {


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
}