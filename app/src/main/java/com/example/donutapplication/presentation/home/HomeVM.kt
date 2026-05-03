package com.example.donutapplication.presentation.home

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.donutapplication.R
import com.example.donutapplication.data.CategoryDummyData
import com.example.donutapplication.data.donutsDummyDataList
import com.example.donutapplication.data.drinksDummyDataList
import com.example.donutapplication.data.icedDrinksDummyDataList
import com.example.donutapplication.presentation.details.DetailsActivity

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

    fun navigateToDetailsScreen(context: Context,categoryID:Int, itemID:Int){
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("categoryID",categoryID)
        intent.putExtra("itemID",itemID)
        context.startActivity(intent)
    }
}