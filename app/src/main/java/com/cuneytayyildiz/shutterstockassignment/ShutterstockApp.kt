package com.cuneytayyildiz.shutterstockassignment

import android.app.Application
import com.cuneytayyildiz.shutterstockassignment.di.components.AppComponent


class ShutterstockApp : Application() {

    companion object {
        //JvmStatic allow access it from java code
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this)
    }
}