package com.sadondev.thamanya_assignment.di

import com.sadondev.thamanya_assignment.data.remote.DefaultThamanyaAPI
import com.sadondev.thamanya_assignment.data.remote.KtorClient
import org.koin.dsl.module


val remoteModule = module {
    single {
        KtorClient().getClient()
    }
    single {
        DefaultThamanyaAPI(client = get())
    }
}


