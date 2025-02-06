package di

import domain.HomeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import presentation.HomeViewModel

fun commonModule() = networkModule() + module {
    singleOf(::HomeRepository)
    viewModelOf(::HomeViewModel)
}