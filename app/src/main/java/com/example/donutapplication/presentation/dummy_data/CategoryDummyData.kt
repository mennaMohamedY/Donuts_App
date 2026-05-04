package com.example.donutapplication.presentation.dummy_data

import com.example.donutapplication.R

data class CategoryDummyData(
    val itemImag:Int,
    val itemName: String,
    val itemPrice: Double,
    val itemDescriotion:String
)

val drinksDummyDataList : List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.tea_img1, itemName = "Original Coffee",4.75 ,"original coffee"),
    CategoryDummyData(itemImag = R.drawable.cofee_img1, itemName = "Tea",4.00,"black tea, water, and milk, often sweetened to taste."),
    CategoryDummyData(itemImag = R.drawable.tea_img3, itemName = "Espresso",3.75,"black tea, water, and milk, often sweetened to taste."),
)

val icedDrinksDummyDataList :List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.tea_img2, itemName = "Iced Latte",6.75,"brewed coffee, milk, ice, and optional sweetener."),
    CategoryDummyData(itemImag = R.drawable.cofee_img2, itemName = "Iced Macchiato",7.00,"brewed coffee, milk, ice, and optional sweetener."),
    CategoryDummyData(itemImag = R.drawable.cofee_img3, itemName = "Iced Cofee with Milk",5.50,"brewed coffee, milk, ice, and optional sweetener."),
)

val donutsDummyDataList :List<CategoryDummyData> = listOf(
    CategoryDummyData(itemImag = R.drawable.donut1, itemName = "Rasberry Vanilla",7.75,"flour, sugar, eggs, liquid (milk or water), fat (butter or oil), leavening agents (yeast or baking powder), and salt"),
    CategoryDummyData(itemImag = R.drawable.donut2, itemName = "Tripple Chocolate",9.75,"flour, sugar, eggs, liquid (milk or water), fat (butter or oil), leavening agents (yeast or baking powder), and salt"),
    CategoryDummyData(itemImag = R.drawable.donut3, itemName = "Classic Sprinkle",5.50 ,"flour, sugar, eggs, liquid (milk or water), fat (butter or oil), leavening agents (yeast or baking powder), and salt"),
)


val featuredDummyList : List<CategoryDummyData> = listOf(
    CategoryDummyData(
        R.drawable.donut_img1,
        "Pumpkin Spice",
        4.00,
        "flour, sugar, eggs, liquid (milk or water), fat (butter or oil), leavening agents (yeast or baking powder), and salt"
    ),
    CategoryDummyData(
        R.drawable.donut_img2,
        "Strawberry Lemon",
        3.75,
        "flour, sugar, eggs, liquid (milk or water), fat (butter or oil), leavening agents (yeast or baking powder), and salt"
    )
)