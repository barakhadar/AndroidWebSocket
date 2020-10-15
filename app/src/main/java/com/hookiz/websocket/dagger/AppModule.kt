package com.hookiz.websocket.dagger


import com.hookiz.websocket.objects.App
import com.hookiz.websocket.server.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule( var app: App) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideRepository() = Repository( app )
}