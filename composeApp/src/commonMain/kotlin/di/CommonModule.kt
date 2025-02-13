package di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import presentation.auth.AuthViewModel
import presentation.details.DetailsViewModel
import presentation.home.HomeViewModel

fun commonModule() = networkModule() + domainModule() + module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::AuthViewModel)
}