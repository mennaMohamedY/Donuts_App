package com.example.donutapplication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HideSource
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donutapplication.R



@Composable
fun SignUpPage(vm: LoginVM,navigateToMain:()-> Unit){
    Column(Modifier.padding(26.dp,12.dp,26.dp,0.dp)
    ) {


        RegisterTextField(vm.email.value, { newEmail-> vm.setEmail(newEmail) },vm.emailErrorMsg.value)
        RegisterPassTextField(vm.password.value,{ newPass-> vm.setPassword(newPass) },vm.passwordError.value)
        SignInBtn(onSignInClick = {
            val isValid = vm.signIn()
            if (isValid) navigateToMain() })
        Image(painter = painterResource(R.drawable.line_seperator), contentDescription = "line seperator",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 19.dp, horizontal = 20.dp), contentScale = ContentScale.FillWidth )
        SignInViaMetaSection({methodID->
            vm.signInViaMeta(methodID)})
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

        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 12.dp, 0.dp, 0.dp))
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

        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 6.dp, 0.dp, 0.dp))
}

@Composable
fun SignInBtn(onSignInClick:()->Unit){
    Button(onClick = onSignInClick , colors = ButtonColors(
        containerColor = colorResource(R.color.primary),
        contentColor = Color.White , disabledContainerColor = colorResource(R.color.primary_disabled),
        disabledContentColor = Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 26.dp, horizontal = 45.dp)) {
        Text("Sign In", fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SignInViaMetaSection(onSignInClick:(Int)->Unit){
    Row(Modifier
        .fillMaxWidth()
        .padding(vertical = 19.dp, horizontal = 20.dp)) {
        listOf(R.drawable.facebook_icon,R.drawable.google_icon).forEachIndexed { index, iconID ->
            SignInWithFBOrGoogleBtn(iconID,index, modifier = Modifier.weight(0.50f)) { loginMethodID->
                onSignInClick(loginMethodID)
            }
        }
    }

}
@Composable
fun SignInWithFBOrGoogleBtn(icon:Int,loginMethodID:Int,modifier: Modifier,onClick:(Int)->Unit){

    Box(
        contentAlignment = if (loginMethodID == 0) Alignment.CenterEnd else Alignment.CenterStart,
        modifier = modifier.padding(4.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .size(44.dp)
                .border(
                    1.dp, colorResource(R.color.light_gray),
                    shape = CircleShape
                )
                .clickable {
                    onClick(loginMethodID)
                }) {

            val content = "Login via" + if(loginMethodID ==0) "facebook" else "google"
            Image(painterResource(icon), contentDescription = content, Modifier
                .padding(2.dp)
                .size(24.dp),
            )
        }
    }
}