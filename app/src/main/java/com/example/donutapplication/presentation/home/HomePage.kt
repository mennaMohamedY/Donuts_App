package com.example.donutapplication.presentation.home

import android.util.Log
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardControlKey
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donutapplication.R
import com.example.donutapplication.presentation.common.CartHoneVMFactory
import com.example.donutapplication.presentation.dummy_data.CategoryDummyData
import com.example.donutapplication.presentation.dummy_data.categoriesDummyList
import com.example.donutapplication.presentation.dummy_data.dummyLocationList
import com.example.donutapplication.presentation.dummy_data.featuredDummyList
import com.example.donutapplication.presentation.login.TabDesign
import kotlinx.coroutines.launch

@Composable
fun HomePage(modifier: Modifier ){
    val vm : HomeVM = viewModel(
        factory = CartHoneVMFactory(LocalContext.current)
    )

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        vm.refreshItemsInCart()
    }

    val itemsInCart = vm.itemsCart.value

    val selectedCategoryTabState = rememberPagerState(
        pageCount = {categoriesDummyList.size}
    )
    val scrollState = rememberScrollState()
    val coroutineScop = rememberCoroutineScope ()
    val context = LocalContext.current
    Column(modifier.fillMaxSize().verticalScroll(scrollState).background(colorResource(R.color.home_bg_color))) {
        LocationAndCart(itemsInCart)
        Row(Modifier.padding(vertical = 12.dp, horizontal = 24.dp)) {
            Text("NEW " , fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("Pumpkin spice donut!" , fontSize = 14.sp)
        }
        Titles("Featured")
        Row(Modifier.padding(vertical = 4.dp, horizontal = 24.dp)) {

            featuredDummyList.forEachIndexed { index, data ->
                FeaturedCard(index,data.itemName,data.itemPrice, data.itemImag,Modifier.weight(0.5f)){
                    Log.e("categoryDetails","2-category id is 3, item id is $index")
                    vm.navigateToDetailsScreen(context, categoryID = 3, itemID = index)
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
                0, 1,2 -> {SelectedCategoryPage(categoryItemsList){itemId->
                    Log.e("categoryDetails","1-category id is $page, item id is $itemId")
                    vm.navigateToDetailsScreen(context, categoryID = page, itemID = itemId)
                }}
            }
        }
    }
}

@Composable
fun LocationAndCart(itemsInCart: Int){
    var isDropDownExpanded by remember { mutableStateOf(false) }
    var menuSelectedItem by remember { mutableStateOf(dummyLocationList[1]) }
    Row(Modifier.padding(24.dp,12.dp,24.dp,10.dp).fillMaxWidth()) {

        Box(Modifier.padding(vertical = 12.dp, horizontal = 3.dp).weight(1f),
            contentAlignment = Alignment.CenterStart){
            Card(shape = RoundedCornerShape(32.dp), elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(Color.White), onClick = {
                    isDropDownExpanded = true
                }) {
                Row(modifier = Modifier.padding(vertical = 6.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location Icon"
                    , tint = colorResource(R.color.primary))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = menuSelectedItem, fontSize = 12.sp)
                    Icon(if (!isDropDownExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardControlKey, contentDescription = "dropDown",
                        tint = colorResource(R.color.price_color), modifier = Modifier.padding(2.dp))
                }
                DropdownMenu(
                    expanded = isDropDownExpanded,
                    onDismissRequest = { isDropDownExpanded = false }
                ) {
                    dummyLocationList.forEachIndexed { _, string ->
                        DropdownMenuItem(
                            text = { Text(string) },
                            onClick = {
                                isDropDownExpanded = false
                                menuSelectedItem = string

                            }
                        )
                    }
                }
            }
        }
        CardWithImage(Icons.Default.AddShoppingCart  ,R.color.primary,true, itemsInCart){
            //navigate to cart page
        }

    }
}
@Composable
fun CardWithImage(icon: ImageVector, iconCol:Int, showNotificationBage: Boolean= false,itemsInCart:Int=0, onItemClick:()->Unit){
    Box(contentAlignment = if(showNotificationBage) Alignment.BottomStart else Alignment.Center){
        Card(modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp).clickable{
            onItemClick()
        },shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(Color.White)) {
            BadgedBox(
                badge= {
                    if (showNotificationBage){
                        Badge(modifier = Modifier.padding(1.dp)) {
                            Text("$itemsInCart", fontSize = 7.sp)
                        }
                    }
                }
            ) {
                Icon(imageVector = icon,"shopping cart",
                    tint = colorResource(iconCol), modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp))

            }
        }
    }
}

@Composable
fun FeaturedCard(id:Int, donutNam:String, donutPrice: Double, donutIcon:Int, modifier: Modifier, onItemClick: () -> Unit){

    Card(shape = RoundedCornerShape(24.dp), modifier = modifier.
    padding(if (id ==0) 0.dp else 8.dp,0.dp,if (id ==0) 8.dp else 0.dp,0.dp).clickable{
        onItemClick()
    },
        colors = CardDefaults.cardColors(Color.White)) {

        Column(Modifier.padding(horizontal = 5.dp, vertical = 5.dp)) {
            Image(painterResource(donutIcon), contentDescription = "donut img",
                modifier = Modifier.padding(12.dp).size(146.dp), alignment = Alignment.Center, )

            Text(donutNam, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = colorResource(R.color.price_color), maxLines = 1)

            Text("$ $donutPrice", fontSize = 10.sp, fontWeight = FontWeight.Medium, color = colorResource(R.color.price_color))
        }
    }
}

@Composable
fun Titles(title:String){
    Text(title , fontSize = 11.sp,fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(24.dp,22.dp,24.dp,6.dp))
}

@Composable
fun SelectedCategoryPage(categoryItems:List<CategoryDummyData>,onItemClick: (Int) -> Unit){

    Column  {
        categoryItems.forEachIndexed { index, categoryItem ->
            Row(Modifier.padding(vertical = 6.dp, horizontal = 24.dp).clickable{
                onItemClick(index)
            }) {
                Image(painterResource(categoryItem.itemImag), contentDescription = "categoryItemImage",
                    Modifier.size(68.dp), contentScale = ContentScale.Fit)
                Text(categoryItem.itemName, color = colorResource(R.color.price_color), fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 26.dp, vertical = 4.dp) )
            }
        }

    }
}
