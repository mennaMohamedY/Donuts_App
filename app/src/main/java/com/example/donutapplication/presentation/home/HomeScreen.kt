package com.example.donutapplication.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.donutapplication.R
import com.example.donutapplication.data.bottomNavList

@Composable
fun HomeScreen(){

    var bottomNavSelectedIndex by remember { mutableIntStateOf(0) }
    Scaffold(modifier = Modifier.fillMaxSize().padding(0.dp,14.dp,0.dp,0.dp),
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                bottomNavList.forEachIndexed { index, item ->
                    NavigationBarItem(


                        colors = NavigationBarItemColors(
                            selectedIconColor = colorResource(R.color.primary),
                            selectedTextColor = colorResource(R.color.primary),
                            unselectedTextColor = colorResource(R.color.light_gray),
                            unselectedIconColor = colorResource(R.color.light_gray),
                            disabledIconColor = colorResource(R.color.light_gray),
                            disabledTextColor = colorResource(R.color.light_gray),
                            selectedIndicatorColor = colorResource(R.color.light_gray),
                        ),
                        selected = bottomNavSelectedIndex == index,
                        onClick = {
                            bottomNavSelectedIndex = index
                        },
                        icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                        label = { Text(text = item.label, fontSize = 8.sp, fontWeight = FontWeight.Bold) },

                        )
                }

            }
        }) { innerPading->
        HomeContent(modifier = Modifier.padding(innerPading),bottomNavSelectedIndex)
    }
}

@Composable
fun HomeContent(modifier: Modifier,selectedIndex:Int){

    when (selectedIndex){
        0-> {HomePage(modifier)}
        1-> {Text("Orders Page")}
        2-> {Text("Gifts Page")}
        3-> {Text("Personal Page")}
    }

}
