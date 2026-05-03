package com.example.donutapplication.presentation.details

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.donutapplication.data.CategoryDummyData
import com.example.donutapplication.data.donutsDummyDataList
import com.example.donutapplication.data.drinksDummyDataList
import com.example.donutapplication.data.featuredDummyList
import com.example.donutapplication.data.icedDrinksDummyDataList

class DetailsVM : ViewModel() {

    private var _itemDetails by mutableStateOf<CategoryDummyData?>(null)
    val itemDetails = derivedStateOf { _itemDetails }



    fun getDataByID(categoryID:Int,itemID:Int) {
         val selectedCategoryList =  when(categoryID){
            0-> drinksDummyDataList
            1-> icedDrinksDummyDataList
            2-> donutsDummyDataList
            else -> featuredDummyList
        }
        Log.e("categoryDetails","in details vm category id is$categoryID, itemID is $itemID, selectedList is $selectedCategoryList")

        getItemDetails(selectedCategoryList,itemID)
    }

    private fun getItemDetails(categoryList:List<CategoryDummyData>,itemID:Int){
        _itemDetails = categoryList[itemID]
    }
}