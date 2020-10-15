package com.hookiz.websocket.dagger

import com.hookiz.websocket.objects.App
import com.hookiz.websocket.ui.FeedViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ AppModule::class ] )
interface AppComponent {
    fun inject( app : App)
    fun inject( feedViewModel : FeedViewModel)
}