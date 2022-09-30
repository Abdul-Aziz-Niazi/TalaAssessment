package com.abdulaziz.tala.home.domain

import androidx.lifecycle.MutableLiveData
import com.abdulaziz.network.ApiResult
import com.abdulaziz.network.ErrorResponse
import com.abdulaziz.tala.BaseTest
import com.abdulaziz.tala.home.data.DataRepository
import com.abdulaziz.tala.home.data.LocaleData
import com.abdulaziz.tala.managers.unzip
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DefaultGetLocaleDataUseCaseTest:BaseTest() {
    @Spy
    lateinit var repository: DataRepository
    lateinit var defaultGetLocaleDataUseCase: GetLocaleDataUseCase
    private interface Callback : (com.abdulaziz.tala.home.data.LocaleData) -> Unit
    private interface ErrorCallback : (String) -> Unit
    @Before
    fun setup() {
        defaultGetLocaleDataUseCase = DefaultGetLocaleDataUseCase(repository)
    }

    @Test
    fun `error response produces empty result`(): Unit = runBlocking {
        whenever(repository.getLocaleData()).thenReturn(ApiResult.Failure(ErrorResponse("Something Went Wrong", 500)))
        val mutableLiveData = defaultGetLocaleDataUseCase.execute()
        val success = mock<Callback>()
        val failureCallback = mock<ErrorCallback>()
        unzip({repository.getLocaleData()},success,failureCallback::invoke)
        verify(failureCallback).invoke(any())
        assertEquals(mutableLiveData.value, LocaleData.EMPTY)
    }
}