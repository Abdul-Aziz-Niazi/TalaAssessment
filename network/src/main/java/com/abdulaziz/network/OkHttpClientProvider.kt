package com.abdulaziz.network

import android.content.Context
import com.abdulaziz.network.interceptors.LocalResponseInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

interface OKHttpClientProvider : Provider<OkHttpClient>

class DefaultOKHttpClientProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : OKHttpClientProvider {

    private companion object {
        const val TIMEOUT = 30
    }

    override fun get(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(LocalResponseInterceptor(context))
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }
}
