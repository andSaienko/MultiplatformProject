package di

import data.repo.ProductsRepositoryImpl
import domain.repo.ProductsRepository
import domain.usecase.SignInUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

fun domainModule() = module {
    singleOf(::ProductsRepositoryImpl).bind<ProductsRepository>()
    factoryOf(::SignInUseCase)
}