package com.example.donutapplication.presentation.details

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donutapplication.R
import com.example.donutapplication.presentation.details.ui.theme.DonutApplicationTheme
import com.example.donutapplication.presentation.home.CardWithImage

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val categoryID : Int = intent.getIntExtra("categoryID",0)
        val itemID : Int = intent.getIntExtra("itemID",0)

        setContent {
            val vm : DetailsVM = viewModel()
            val context = LocalContext.current
            vm.getDataByID(categoryID,itemID)

            val item = vm.itemDetails.value
            DonutApplicationTheme {
                Column(Modifier.fillMaxSize().background(colorResource(R.color.home_bg_color))
                    .padding(14.dp,24.dp,14.dp,10.dp)) {
                    CardWithImage(Icons.Default.ArrowBackIosNew,R.color.black){
                        (context as? Activity)?.finish()
                    }
                    Image(alignment = Alignment.Center,
                        painter = painterResource(item?.itemImag?:R.drawable.donut1),
                        contentDescription = item?.itemName,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(335.dp).padding(horizontal = 34.dp, vertical = 2.dp)
                        )
                }
            }
        }
    }
}


@Composable
fun ItemDetailsSection(){
    Column() {
        Card(shape = RoundedCornerShape(30.dp,30.dp,0.dp,0.dp),
            colors = CardDefaults.cardColors(Color.White)) {

        }
    }
}


