package com.abdulaziz.network.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class LocalResponseInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (chain.request().url.toString().contains(LOAN_DATA)) {
            val responseString = JSONArray(loadJSONFromAsset(LOAN_DATA))

            return Response.Builder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString.toString())
                .request(chain.request())
                .body(responseString.toString().toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            val responseString = loadJSONFromAsset(LOCALE)?.let { JSONObject(it) }
            return Response.Builder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString.toString())
                .request(chain.request())
                .body(responseString.toString().toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
                .addHeader("content-type", "application/json")
                .build()
        }
    }

    private fun loadJSONFromAsset(url: String): String? {
        val json = try {
            val fileName = if (url == LOAN_DATA) "test_data.json" else "locales.json"
            val inputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
    companion object{
        const val LOCALE = "fake-locale"
        const val LOAN_DATA = "fake-loan"
    }
}