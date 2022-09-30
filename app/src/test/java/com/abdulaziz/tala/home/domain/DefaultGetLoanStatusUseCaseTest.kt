package com.abdulaziz.tala.home.domain

import com.abdulaziz.network.ApiResult
import com.abdulaziz.network.ErrorResponse
import com.abdulaziz.tala.BaseTest
import com.abdulaziz.tala.home.data.DataRepository
import com.abdulaziz.tala.home.data.LoanResponse
import com.abdulaziz.tala.home.data.LocaleData
import com.abdulaziz.tala.managers.unzip
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DefaultGetLoanStatusUseCaseTest:BaseTest() {
    @Mock
    lateinit var repository: DataRepository
    lateinit var defaultGetLoanStatusUseCase: GetLoanStatusUseCase
    private interface Callback : (com.abdulaziz.tala.home.data.LoanResponse) -> Unit
    private interface ErrorCallback : (String) -> Unit
    @Before
    fun setUp() {
        defaultGetLoanStatusUseCase = DefaultGetLoanStatusUseCase(repository)
    }
    @Test
    fun `error response produces empty result`(): Unit = runBlocking {
        whenever(repository.getLoanData()).thenReturn(ApiResult.Failure(ErrorResponse("Something Went Wrong", 500)))
        val mutableLiveData = defaultGetLoanStatusUseCase.execute()
        val success = mock<Callback>()
        val failureCallback = mock<ErrorCallback>()
        unzip({repository.getLoanData()},success,failureCallback::invoke)
        verify(failureCallback).invoke(any())
        assertEquals(mutableLiveData.value, LoanResponse.EMPTY)
    }
}