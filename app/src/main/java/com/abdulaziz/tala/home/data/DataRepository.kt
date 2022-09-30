package com.abdulaziz.tala.home.data

import com.abdulaziz.network.ApiResult
import com.abdulaziz.network.BaseRepository
import javax.inject.Inject

interface DataRepository : BaseRepository {
    suspend fun getLoanData(): ApiResult<LoanResponse>
    suspend fun getLocaleData(): ApiResult<LocaleData>
}

class DefaultDataRepository @Inject constructor(
    private val apiService: APIService
) : DataRepository {
    override suspend fun getLoanData(): ApiResult<LoanResponse> {
        return execute { apiService.getData() }
    }

    override suspend fun getLocaleData(): ApiResult<LocaleData> {
        return execute { apiService.getLocaleData() }
    }

}