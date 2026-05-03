package com.example.donutapplication.data

import com.example.donutapplication.R

data class CategoryDummyData(
    val itemImag:Int,
    val itemName: String,
    val itemPrice:String
)

val drinksDummyDataList : List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.tea_img1, itemName = "Original Coffee","$ 4.75 "),
    CategoryDummyData(itemImag = R.drawable.cofee_img1, itemName = "Tea","$ 4.00 "),
    CategoryDummyData(itemImag = R.drawable.tea_img3, itemName = "Espresso","$ 3.75 "),
)

val icedDrinksDummyDataList :List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.tea_img2, itemName = "Iced Latte","$ 6.75 "),
    CategoryDummyData(itemImag = R.drawable.cofee_img2, itemName = "Iced Macchiato","$ 7.00 "),
    CategoryDummyData(itemImag = R.drawable.cofee_img3, itemName = "Iced Cofee with Milk","$ 5.50 "),
)

val donutsDummyDataList :List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.donut1, itemName = "Rasberry Vanilla","$ 7.75 "),
    CategoryDummyData(itemImag = R.drawable.donut2, itemName = "Tripple Chocolate","$ 9.75 "),
    CategoryDummyData(itemImag = R.drawable.donut3, itemName = "Classic Sprinkle","$ 5.50 "),
)


val featuredDummyList : List<CategoryDummyData> = listOf(
    CategoryDummyData(
        R.drawable.donut_img1,
        "Pumpkin Spice",
        "$ 4.00",
    ),
    CategoryDummyData(
        R.drawable.donut_img2,
        "Strawberry Lemon",
        "$ 3.75",
    )
)