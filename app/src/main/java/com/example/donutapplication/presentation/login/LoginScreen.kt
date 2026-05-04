package com.example.donutapplication.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donutapplication.R
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navigateToMain:()->Unit){

    val selectedTabState = rememberPagerState(
        pageCount = {2}
    )
    Box(Modifier
        .fillMaxSize()
        .paint(
            painter = painterResource(R.drawable.sign_in_screen_bg),
            contentScale = ContentScale.FillBounds
        )) {
        Card(shape = RoundedCornerShape(34.dp), elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 46.dp)
       ) {
            Column {
                LoginTabs(pagerSate = selectedTabState)

                HorizontalPager(state = selectedTabState) { page ->
                    when (page) {
                        0 -> SignInPage  {navigateToMain()}
                        1 -> SignUpPage  {navigateToMain()}
                    }
                }
            }

        }
    }
}

@Composable
fun LoginTabs(pagerSate: PagerState){
    val coutineScope = rememberCoroutineScope ()
    Row(
        modifier = Modifier
            .padding(26.dp, 48.dp, 26.dp, 0.dp)
            .clip(RoundedCornerShape(34.dp))
            .border(
                width = 1.dp,
                color = colorResource(R.color.light_gray),
                shape = RoundedCornerShape(34.dp)
            )

    ) {

        listOf("Sign In", "Sign Up").forEachIndexed { index, title ->
            TabDesign(Modifier.weight(0.50f), isSelected = pagerSate.currentPage ==index,
                title,index){tabId->
                coutineScope.launch {
                    pagerSate.scrollToPage(tabId)
                }
            }
        }
    }
}

@Composable
fun TabDesign(modifier: Modifier, isSelected: Boolean, tabText:String,tabID:Int,onTabClick:(Int)->Unit){

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(34.dp))
            .background(
                if (isSelected) colorResource(R.color.primary) else Color.White
            )
            .clickable { onTabClick(tabID) }
            .padding(vertical = 5.dp, horizontal = 25.dp)
    ) {
        Text(text = tabText, fontSize = 12.sp,
            color = if(isSelected) Color.White else colorResource(R.color.primary),
        )
    }
}
