package com.abdulaziz.network

import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

interface RetrofitProvider : Provider<Retrofit>

class DefaultRetrofitProvider @Inject constructor(
    private val okHttpClientProvider: OKHttpClientProvider,
) : RetrofitProvider {

    override fun get(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClientProvider.get())
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.prettyGson))
            .build()
    }
}