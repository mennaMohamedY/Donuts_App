package com.example.donutapplication.data

import com.example.donutapplication.R

data class CategoryDummyData(
    val itemImag:Int,
    val itemName: String
)

val drinksDummyDataList : List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.tea_img1, itemName = "Original Coffee"),
    CategoryDummyData(itemImag = R.drawable.cofee_img1, itemName = "Tea"),
    CategoryDummyData(itemImag = R.drawable.tea_img3, itemName = "Espresso"),
)

val icedDrinksDummyDataList :List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.tea_img2, itemName = "Iced Latte"),
    CategoryDummyData(itemImag = R.drawable.cofee_img2, itemName = "Iced Macchiato"),
    CategoryDummyData(itemImag = R.drawable.cofee_img3, itemName = "Iced Cofee with Milk"),
)

val donutsDummyDataList :List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.donut1, itemName = "Rasberry Vanilla"),
    CategoryDummyData(itemImag = R.drawable.donut2, itemName = "Tripple Chocolate"),
    CategoryDummyData(itemImag = R.drawable.donut3, itemName = "Classic Sprinkle"),
)