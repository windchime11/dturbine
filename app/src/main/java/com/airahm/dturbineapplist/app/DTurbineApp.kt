package com.airahm.dturbineapplist.app

import android.app.Application
import com.airahm.dturbineapplist.di.serviceModule
import com.airahm.dturbineapplist.di.viewModule
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin

class DTurbineApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        startKoin(this, listOf(serviceModule, viewModule))

    }
}