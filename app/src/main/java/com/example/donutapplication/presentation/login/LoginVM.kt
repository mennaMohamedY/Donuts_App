package com.example.donutapplication.presentation.login

import android.util.Patterns
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginVM : ViewModel() {


    private var _email = mutableStateOf("email")
    val email = derivedStateOf { _email.value }
    private val _emailError = mutableStateOf<String?>(null)
    val emailErrorMsg = derivedStateOf { _emailError.value }

    private var _password = mutableStateOf("password")
    val password = derivedStateOf { _password.value }
    private val _passwordError = mutableStateOf<String?>(null)
    val passwordError = derivedStateOf { _passwordError.value }


    fun setEmail(newEmail:String){
        _email.value = newEmail
    }
    fun setPassword(newPass:String){
        _password.value = newPass
    }


    private fun validateEmail() {
        val value = _email.value
         _emailError.value =  when {
            value.isEmpty() -> "Email cannot be empty"
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> "Enter a valid email 2"
            else -> null
        }

    }

    private fun validatePassword(){
        val value = _password.value
        _passwordError.value = when {
            value.isEmpty()   -> "Password cannot be empty"
            value.length < 8  -> "Password must be at least 8 characters"
            !value.any { it.isUpperCase() } -> "Must contain at least one uppercase letter"
            !value.any { it.isDigit() }     -> "Must contain at least one number"
            !value.any { !it.isLetterOrDigit() } -> "Must contain at least one special character"
            else -> null
        }
    }

    fun signIn(){
        validateEmail()
        validatePassword()
    }

    fun signInViaMeta(mthodID:Int){
        if (mthodID == 0){

        }else{

        }
    }
}