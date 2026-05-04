package com.example.donutapplication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.donutapplication.R

@Composable
fun SignUpPage( vm: LoginVM = viewModel(),navigateToMain:()-> Unit){

    DisposableEffect(Unit) {
        onDispose {
            vm.clearErrors()
        }
    }
    Column(Modifier.padding(26.dp,12.dp,26.dp,0.dp)
    ) {


        RegisterTextField(vm.email.value, { newEmail-> vm.setEmail(newEmail) },vm.emailErrorMsg.value)
        RegisterPassTextField(vm.password.value,{ newPass-> vm.setPassword(newPass) },vm.passwordError.value)
        RegisterPassTextField(vm.confirmPassword.value,{ newPass-> vm.setConfirmPassword(newPass) },vm.confirmPasswordError.value)

        SignInBtn("Sign Up")  {
            val isValid = vm.signUp()
            if (isValid) navigateToMain() }
        Image(painter = painterResource(R.drawable.line_seperator), contentDescription = "line seperator",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 19.dp, horizontal = 20.dp), contentScale = ContentScale.FillWidth )
        SignInViaMetaSection { methodID->
            vm.signInViaMeta(methodID)}
    }
}

