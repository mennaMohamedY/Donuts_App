package com.example.donutapplication.presentation.dummy_data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val icon: ImageVector,
    val label: String
)

val bottomNavList : List<NavBarItem> = listOf(
    NavBarItem(
        icon = Icons.Default.Home,
        label = "Home"
    ),
    NavBarItem(
        icon = Icons.Default.Category,
        label = "Orders"
    ),
    NavBarItem(
        icon = Icons.Default.CardGiftcard,
        label = "Rewards"
    ),
    NavBarItem(
        icon = Icons.Default.Person,
        label = "Account"
    ),

)
