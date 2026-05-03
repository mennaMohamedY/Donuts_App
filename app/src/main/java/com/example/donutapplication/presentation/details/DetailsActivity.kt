package com.example.donutapplication.presentation.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.donutapplication.R
import com.example.donutapplication.presentation.details.ui.theme.DonutApplicationTheme
import com.example.donutapplication.presentation.home.CardWithImage

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val categoryID : Int = intent.getIntExtra("categoryID",0)
        val itemID : Int = intent.getIntExtra("categoryID",0)

        setContent {
            DonutApplicationTheme {
                Column(Modifier.fillMaxSize().background(colorResource(R.color.home_bg_color))) {
                    CardWithImage(Icons.Default.ArrowBackIosNew,R.color.black){

                    }
                }
            }
        }
    }
}

