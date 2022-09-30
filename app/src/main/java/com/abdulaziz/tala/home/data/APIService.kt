package com.abdulaziz.tala.home.data

import com.abdulaziz.network.interceptors.LocalResponseInterceptor
import retrofit2.http.GET

interface APIService {
    @GET(LocalResponseInterceptor.LOAN_DATA)
    suspend fun getData():LoanResponse

    @GET(LocalResponseInterceptor.LOCALE)
    suspend fun getLocaleData():LocaleData
}