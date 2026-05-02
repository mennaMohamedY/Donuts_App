package com.example.donutapplication.data

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.donutapplication.R

data class FeaturedDonutsDummyData (
    val donutName :String,
    val donutPrice:String,
    val donutIcon: Int
)


val featuredDummyList : List<FeaturedDonutsDummyData> = listOf(
    FeaturedDonutsDummyData(
        "Pumpkin Spice",
        "\$ 4.00",
        R.drawable.donut_img1
    ),
    FeaturedDonutsDummyData(
        "Strawberry Lemon",
        "\$ 3.75",
        R.drawable.donut_img2
    )
)