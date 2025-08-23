package com.sadondev.thamanya_assignment.di

import com.sadondev.thamanya_assignment.data.DefaultThamanyaRepository
import com.sadondev.thamanya_assignment.data.ThamanyaRepository
import com.sadondev.thamanya_assignment.data.remote.DefaultThamanyaAPI
import com.sadondev.thamanya_assignment.data.remote.KtorClient
import com.sadondev.thamanya_assignment.domain.usecases.GetMainContentUseCase
import org.koin.dsl.module


val remoteModule = module {
    single {
        KtorClient().getClient()
    }
    single {
        DefaultThamanyaAPI(client = get())
    }
}


val repoModule = module {
    single<ThamanyaRepository> { DefaultThamanyaRepository(api = get()) }
}

val useCasesModule = module {
    factory {
        GetMainContentUseCase(repository = get())
    }
}
