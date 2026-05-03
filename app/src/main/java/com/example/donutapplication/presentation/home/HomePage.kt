package com.example.donutapplication.presentation.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donutapplication.App
import com.example.donutapplication.R
import com.example.donutapplication.data.CategoryDummyData
import com.example.donutapplication.data.categoriesDummyList
import com.example.donutapplication.data.featuredDummyList
import com.example.donutapplication.presentation.details.DetailsActivity
import com.example.donutapplication.presentation.login.TabDesign
import kotlinx.coroutines.launch

@Composable
fun HomePage(modifier: Modifier){

    val vm : HomeVM = viewModel()
    val selectedCategoryTabState = rememberPagerState(
        pageCount = {categoriesDummyList.size}
    )
    val scrollState = rememberScrollState()
    val coroutineScop = rememberCoroutineScope ()
    Column(modifier.fillMaxSize().verticalScroll(scrollState).background(colorResource(R.color.home_bg_color))) {
        LocationAndCart()
        Row(Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            Text("NEW " , fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("Pumpkin spice donut!" , fontSize = 14.sp)
        }
        Titles("Featured")
        Row(Modifier.padding(vertical = 4.dp, horizontal = 24.dp)) {
            featuredDummyList.forEachIndexed { index, data ->
                FeaturedCard(index,data.donutName,data.donutPrice, data.donutIcon,Modifier.weight(0.5f)){
                    val context = App.getApplicationContext()
                    val intent = Intent(context, DetailsActivity::class.java)
                    intent.putExtra("categoryID",3)
                    intent.putExtra("itemID",index)
                    context.startActivity(intent)

                }
            }
        }
        Titles("Categories")
        LazyRow (Modifier.fillMaxWidth().padding(vertical = 1.dp, horizontal = 15.dp)) {
            itemsIndexed(categoriesDummyList){index,category ->
                TabDesign(Modifier.padding(vertical = 6.dp, horizontal = 9.dp),
                    isSelected =  index == selectedCategoryTabState.currentPage,
                    tabText = category, tabID = index, onTabClick = {tabID->
                        coroutineScop.launch {
                            selectedCategoryTabState.scrollToPage(tabID)
                        }
                    })
            }
        }

        HorizontalPager(state = selectedCategoryTabState) { page ->
            val categoryItemsList = vm.getListFromID(page)
            when (page) {
                0, 1,2 -> {SelectedCategoryPage(categoryItemsList)}
            }
        }
    }
}

@Composable
fun LocationAndCart(){
    Row(Modifier.padding(24.dp,12.dp,24.dp,10.dp).fillMaxWidth()) {

        Box(Modifier.padding(vertical = 12.dp, horizontal = 3.dp).weight(1f),
            contentAlignment = Alignment.CenterStart){
            Card(shape = RoundedCornerShape(32.dp), elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(Color.White)) {
                Row(modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location Icon"
                    , tint = colorResource(R.color.primary))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Cairo, Egypt", fontSize = 12.sp)
                }
            }
        }
        CardWithImage(Icons.Default.AddShoppingCart,R.color.primary){

        }

    }
}
@Composable
fun CardWithImage(icon: ImageVector,iconCol:Int,onItemClick:()->Unit){
    Box(contentAlignment = Alignment.CenterEnd){
        Card(modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp).clickable{
            onItemClick()
        },shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(Color.White)) {
            Icon(imageVector = icon,"shopping cart",
                tint = colorResource(iconCol), modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp))

        }
    }
}

@Composable
fun FeaturedCard(id:Int,donutNam:String,donutPrice:String,donutIcon:Int,modifier: Modifier,onItemClick: () -> Unit){

    Card(shape = RoundedCornerShape(24.dp), modifier = modifier.
    padding(if (id ==0) 0.dp else 8.dp,0.dp,if (id ==0) 8.dp else 0.dp,0.dp).clickable{
        onItemClick()
    },
        colors = CardDefaults.cardColors(Color.White)) {

        Column(Modifier.padding(horizontal = 5.dp, vertical = 5.dp)) {
            Image(painterResource(donutIcon), contentDescription = "donut img",
                modifier = Modifier.padding(12.dp).size(146.dp), alignment = Alignment.Center, )

            Text(donutNam, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = colorResource(R.color.price_color), maxLines = 1)

            Text(donutPrice, fontSize = 10.sp, fontWeight = FontWeight.Medium, color = colorResource(R.color.price_color))
        }


    }
}

@Composable
fun Titles(title:String){
    Text(title , fontSize = 11.sp,fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(24.dp,22.dp,24.dp,6.dp))
}

@Composable
fun SelectedCategoryPage(categoryItems:List<CategoryDummyData>){

    Column  {
        categoryItems.forEachIndexed { index, categoryItem ->
            Row(Modifier.padding(vertical = 6.dp, horizontal = 24.dp)) {
                Image(painterResource(categoryItem.itemImag), contentDescription = "categoryItemImage",
                    Modifier.size(68.dp), contentScale = ContentScale.FillBounds)
                Text(categoryItem.itemName, color = colorResource(R.color.price_color), fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 26.dp, vertical = 4.dp) )
            }
        }

    }
}