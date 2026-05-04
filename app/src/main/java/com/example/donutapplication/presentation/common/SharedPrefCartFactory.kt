package com.example.donutapplication.presentation.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.donutapplication.presentation.details.DetailsVM
import com.example.donutapplication.presentation.home.HomeVM


class CartDetailsVMFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsVM(SharedPrefCart(context)) as T
    }
}


class CartHoneVMFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeVM(SharedPrefCart(context)) as T
    }
}