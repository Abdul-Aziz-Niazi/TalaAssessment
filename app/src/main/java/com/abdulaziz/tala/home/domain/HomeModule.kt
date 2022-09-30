package com.abdulaziz.tala.home.domain

import com.abdulaziz.network.RetrofitProvider
import com.abdulaziz.tala.home.data.APIService
import com.abdulaziz.tala.home.data.DataRepository
import com.abdulaziz.tala.home.data.DefaultDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface HomeModule {
    @Binds
    fun bindsGetLoanUseCase(default: DefaultGetLoanStatusUseCase): GetLoanStatusUseCase

    @Binds
    fun bindsGetLocaleDataUseCase(default: DefaultGetLocaleDataUseCase): GetLocaleDataUseCase

    @Binds
    fun bindsDataRepository(defaultDataRepository: DefaultDataRepository): DataRepository
}


@Module
@InstallIn(SingletonComponent::class)
class HomeApiModule {
    @Provides
    fun provideAPIService(retrofitProvider: RetrofitProvider): APIService =
        retrofitProvider.get().create()
}