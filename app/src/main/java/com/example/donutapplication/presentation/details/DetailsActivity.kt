package com.example.donutapplication.presentation.details

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardControlKey
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donutapplication.R
import com.example.donutapplication.data.CategoryDummyData
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
                    .padding(0.dp,24.dp,0.dp,10.dp)) {
                    Box(Modifier.padding(14.dp,0.dp,14.dp,0.dp)){
                        CardWithImage(Icons.Default.ArrowBackIosNew,R.color.black){
                            (context as? Activity)?.finish()
                        }
                    }
                    Image(alignment = Alignment.Center,
                        painter = painterResource(item?.itemImag?:R.drawable.donut1),
                        contentDescription = item?.itemName,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(335.dp).padding(horizontal = 34.dp, vertical = 2.dp)
                        )
                    ItemDetailsSection(item)

                }
            }
        }
    }
}


@Composable
fun ItemDetailsSection(item: CategoryDummyData?){
    var itemCount by remember { mutableIntStateOf(1) }
    val itemPrice = item?.itemPrice ?: 1.0

    Column() {
        Card(shape = RoundedCornerShape(30.dp,30.dp,0.dp,0.dp),
            colors = CardDefaults.cardColors(Color.White)) {
            Row(Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
                Text(item?.itemName?:"item name", color = colorResource(R.color.price_color),
                    fontWeight = FontWeight.Bold, fontSize = 22.sp, modifier = Modifier.weight(1f))

                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "item icon", tint = colorResource(R.color.price_color)
                    , modifier = Modifier.padding(6.dp))
            }
            ItemDescription(item)
            Card(shape = RoundedCornerShape(30.dp,30.dp,0.dp,0.dp),
                colors = CardDefaults.cardColors(colorResource(R.color.home_bg_color)),
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 0.dp) ){
                Column(Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
                    Text("price", color = colorResource(R.color.price_color),
                        fontWeight = FontWeight.Normal, fontSize = 12.sp)
                    Row() {
                        Text("$ ${itemPrice*itemCount}", color = colorResource(R.color.price_color),
                            fontWeight = FontWeight.SemiBold, fontSize = 20.sp, modifier = Modifier.weight(1f))

                        Image(painterResource(R.drawable.remove_img), contentDescription = "minus one",
                            modifier = Modifier.size(40.dp).clickable{
                                if(itemCount >1){
                                    itemCount -= 1
                                }
                            })
                        Text("$itemCount", color = colorResource(R.color.price_color),
                            fontWeight = FontWeight.Normal, fontSize = 15.sp,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 0.dp) )
                        Image(painterResource(R.drawable.add_img), contentDescription = "minus one",
                            modifier = Modifier.size(40.dp).clickable{
                                itemCount +=1
                            })

                    }
                    AddToCartBtn(){


                    }
                }
            }

        }
    }
}

@Composable
fun ItemDescription(item: CategoryDummyData?){
    var showMoreDetails by remember { mutableStateOf(false) }

    Row(Modifier.padding(24.dp,10.dp,24.dp,14.dp)) {
        Text(text = "Allergen & Ingredient", fontSize = 12.sp, fontWeight = FontWeight.Normal,
            color = colorResource(R.color.price_color),
        )
        Icon(if (showMoreDetails) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardControlKey, contentDescription = "dropDown",
            tint = colorResource(R.color.price_color), modifier = Modifier.padding(4.dp).clickable{
                showMoreDetails = !showMoreDetails
            })
    }
    if (showMoreDetails){
        Text(text = item?.itemDescriotion?:"", fontSize = 12.sp, fontWeight = FontWeight.Normal,
            color = colorResource(R.color.price_color),
            modifier = Modifier.padding(14.dp,2.dp,14.dp,10.dp)
        )
    }
}


@Composable
fun AddToCartBtn(onAddToBagClick:()->Unit){
    Button(onClick = onAddToBagClick , colors = ButtonColors(
        containerColor = colorResource(R.color.primary),
        contentColor = Color.White , disabledContainerColor = colorResource(R.color.primary_disabled),
        disabledContentColor = Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 26.dp, horizontal = 24.dp)) {
        Text("Add To Cart", fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}