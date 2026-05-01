package com.example.donutapplication.presentation.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HideSource
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donutapplication.R
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(){

    val vm: LoginVM = viewModel()
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

            Column() {
                LoginTabs(pagerSate = selectedTabState)

                HorizontalPager(state = selectedTabState) { page ->
                    when (page) {
                        0 -> SignInContent()
                        1 -> SignUpContent(
                            vm.email.value ,
                            {newEmail-> vm.setEmail(newEmail) },
                            vm.emailErrorMsg.value,
                            vm.password.value,
                            {newPass-> vm.setPassword(newPass)},
                            vm.passwordError.value,
                            onSignInClick = {
                                vm.signIn()
                            })
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
            .padding(26.dp,48.dp,26.dp,0.dp)
            .clip(RoundedCornerShape(34.dp))
            .border(
                width = 1.dp,
                color = colorResource(R.color.light_gray),
                shape = RoundedCornerShape(34.dp)
            )

    ) {

        listOf("Sign In", "Sign Up").forEachIndexed { index, title ->
            TabDesign(Modifier.weight(0.50f), isSelected = pagerSate.currentPage ==index,
                title,index, {tabId->
                    coutineScope.launch {
                        pagerSate.scrollToPage(tabId)
                    }
                })
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
                if (isSelected) colorResource(R.color.primary) else Color.Transparent
            )
            .clickable { onTabClick(tabID) }
            .padding(vertical = 5.dp, horizontal = 25.dp)
    ) {
        Text(text = tabText, fontSize = 12.sp,
            color = if(isSelected) Color.White else colorResource(R.color.primary),
        )
    }
}
@Composable
fun SignInContent(){
    Box(Modifier.padding(12.dp)) {
        Text("helloo")
    }
}
@Composable
fun SignUpContent(email: String,onValueChanged:(String)->Unit,emailErrorMsg: String?,
                  password:String,onPassValueChanged:(String)->Unit,passErrorMsg: String?,
                  onSignInClick: () -> Unit){
    Column(Modifier.padding(26.dp,12.dp,26.dp,0.dp)
        ) {

        RegisterTextField(email,onValueChanged,emailErrorMsg)
        RegisterPassTextField(password,onPassValueChanged,passErrorMsg)
        SignInBtn(onSignInClick = onSignInClick)
    }
}

@Composable
fun RegisterTextField(email: String,onValueChanged:(String)-> Unit,errorMsg: String?){
    TextField(value = email,
        onValueChange = { onValueChanged(it) },
        placeholder = {Text("Email", color = Color.LightGray)},
        isError = errorMsg != null,
        textStyle = TextStyle(
            fontSize = 10.sp,

        ),
        singleLine = true,
        colors = TextFieldDefaults.colors(Color.Black,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent ,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = colorResource(R.color.light_gray),
            unfocusedIndicatorColor = colorResource(R.color.light_gray),
            errorTextColor = Color.Red,
            errorCursorColor = Color.Red
            ),
        supportingText = {
            if(errorMsg != null){
                Text(errorMsg, color = Color.Red)
            }
        },

        modifier = Modifier.fillMaxWidth().padding(0.dp,22.dp,0.dp,0.dp))
}

@Composable
fun RegisterPassTextField(email: String,onValueChanged:(String)-> Unit,errorMsg: String?){
    var passIsVisible by remember { mutableStateOf(false) }
    TextField(value = email,
        onValueChange = { onValueChanged(it) },
        placeholder = {Text("Password", color = Color.LightGray)},
        isError = errorMsg != null,
        textStyle = TextStyle(
            fontSize = 10.sp,

            ),
        singleLine = true,
        visualTransformation = if (passIsVisible) VisualTransformation.None
        else PasswordVisualTransformation()
        ,
        colors = TextFieldDefaults.colors(Color.Black,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent ,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = colorResource(R.color.light_gray),
            unfocusedIndicatorColor = colorResource(R.color.light_gray),
            errorTextColor = Color.Red,
            errorCursorColor = Color.Red
        ),
        supportingText = {
            if(errorMsg != null){
                Text(errorMsg, color = Color.Red)
            }
        },

        trailingIcon = {
            Image(imageVector = if (passIsVisible)  Icons.Filled.HideSource else Icons.Filled.RemoveRedEye , contentDescription = "seePassIcon",
                modifier = Modifier.clickable{

                    passIsVisible = !passIsVisible
                })
        },

        modifier = Modifier.fillMaxWidth().padding(0.dp,22.dp,0.dp,0.dp))
}

@Composable
fun SignInBtn(onSignInClick:()->Unit){
    Button(onClick = onSignInClick , colors = ButtonColors(
        containerColor = colorResource(R.color.primary),
        contentColor = Color.White , disabledContainerColor = colorResource(R.color.primary_disabled),
        disabledContentColor = Color.LightGray),
        modifier = Modifier.fillMaxWidth().padding(vertical =86.dp , horizontal = 45.dp)) {

        Text("Sign In", fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}