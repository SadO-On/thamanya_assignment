package com.sadondev.thamanya_assignment

import android.app.Application
import com.sadondev.thamanya_assignment.di.remoteModule
import com.sadondev.thamanya_assignment.di.repoModule
import com.sadondev.thamanya_assignment.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                remoteModule, repoModule, useCasesModule
            )
        }
    }
}