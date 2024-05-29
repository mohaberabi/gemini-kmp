package org.mohaberabi.geminichat

import android.app.Application
import shared.KoinInit

class GeminiChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInit(this).init()
    }
}