package com.sadondev.thamanya_assignment.di

import com.sadondev.thamanya_assignment.data.DefaultThamanyaRepository
import com.sadondev.thamanya_assignment.data.ThamanyaRepository
import com.sadondev.thamanya_assignment.data.remote.DefaultThamanyaAPI
import com.sadondev.thamanya_assignment.data.remote.KtorClient
import com.sadondev.thamanya_assignment.data.remote.ThamanyaAPI
import com.sadondev.thamanya_assignment.domain.usecases.GetMainContentUseCase
import com.sadondev.thamanya_assignment.domain.usecases.SearchContentUseCase
import com.sadondev.thamanya_assignment.ui.dashboard.DashboardViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val remoteModule = module {
    single {
        KtorClient().getClient()
    }

    singleOf(::DefaultThamanyaAPI) { bind<ThamanyaAPI>() }

}


val repoModule = module {
    single<ThamanyaRepository> { DefaultThamanyaRepository(api = get()) }

}

val useCasesModule = module {
    factory {
        GetMainContentUseCase(repository = get())
    }
    factory {
        SearchContentUseCase(repository = get())
    }
}

val viewModelModule = module {
    viewModel {
        DashboardViewModel(useCase = get())
    }
}