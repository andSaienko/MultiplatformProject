package di

import data.repo.ProductsRepositoryImpl
import domain.ProductsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.auth.AuthViewModel
import presentation.details.DetailsViewModel
import presentation.home.HomeViewModel

fun commonModule() = networkModule() + module {
    singleOf(::ProductsRepositoryImpl).bind<ProductsRepository>()
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::AuthViewModel)
}