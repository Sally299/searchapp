package com.nurmamedova.searchapp

import android.app.Application
import com.nurmamedova.searchapp.di.AppComponent
import com.nurmamedova.searchapp.di.DaggerAppComponent

class SearchApp: Application() {
    val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()
}