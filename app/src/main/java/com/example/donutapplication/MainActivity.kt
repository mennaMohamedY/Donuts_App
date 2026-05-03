package com.example.donutapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.donutapplication.presentation.home.HomeScreen
import com.example.donutapplication.presentation.login.LoginScreen
import com.example.donutapplication.ui.theme.DonutApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DonutApplicationTheme {
               SetNavGraph()
            }
        }
    }
}


@Composable
fun SetNavGraph(){
    val navController = rememberNavController()

    NavHost(navController,"LoginScreen") {
//        composable("LoginScreen") {
//
//            LoginScreen(navigateToMain = {
//                navController.navigate("LoginScreen/homeScreen")
//            })
//        }

        //LoginScreen/homeScreen
        composable("LoginScreen") {
            HomeScreen()
        }
    }
}



