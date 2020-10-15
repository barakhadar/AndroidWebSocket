package com.hookiz.websocket.objects

import android.app.Application
import com.hookiz.websocket.dagger.AppComponent
import com.hookiz.websocket.dagger.AppModule
import com.hookiz.websocket.dagger.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule( AppModule( this ) )
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject( this )
    }

}

