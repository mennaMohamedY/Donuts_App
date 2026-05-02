package com.example.donutapplication

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        applicaton = this
    }

    companion object{
        private lateinit var  applicaton : App
        fun getApplicationContext() = applicaton.applicationContext
    }

}