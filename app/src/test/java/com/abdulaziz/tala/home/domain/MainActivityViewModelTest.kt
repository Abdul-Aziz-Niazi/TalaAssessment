package com.abdulaziz.tala.home.domain

import androidx.lifecycle.MutableLiveData
import com.abdulaziz.tala.home.data.LoanResponse
import com.abdulaziz.tala.home.data.LocaleData
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {
    @Mock
    lateinit var getLocaleDataUseCase: GetLocaleDataUseCase

    @Mock
    lateinit var getLoanStatusUseCase: GetLoanStatusUseCase

    private lateinit var viewModel: MainActivityViewModel

    @Before
    fun setUp() {
        viewModel = MainActivityViewModel(getLoanStatusUseCase, getLocaleDataUseCase)
    }

    @Test
    fun `empty loan response on error`() {
        whenever(getLoanStatusUseCase.execute()).thenReturn(MutableLiveData(LoanResponse.EMPTY))
        val loanResponse = viewModel.getLoanData().value
        assertNotNull(loanResponse)
        assertEquals(loanResponse, LoanResponse.EMPTY)
    }

    @Test
    fun `empty locale response on error`() {
        whenever(getLocaleDataUseCase.execute()).thenReturn(MutableLiveData(LocaleData.EMPTY))
        val localeResponse = viewModel.getLocaleData().value
        assertNotNull(localeResponse)
        assertEquals(localeResponse, LocaleData.EMPTY)
    }

    @Test
    fun `success loan result is not null`() {
        whenever(getLoanStatusUseCase.execute()).thenReturn(MutableLiveData(Gson().fromJson(getTestData(), LoanResponse::class.java)))
        assertNotNull(viewModel.getLoanData().value)
    }
    @Test
    fun `success locale result is not null`() {
        whenever(getLocaleDataUseCase.execute()).thenReturn(MutableLiveData(Gson().fromJson(getLocaleTestData(), LocaleData::class.java)))
        assertNotNull(viewModel.getLocaleData().value)
    }

    private fun getTestData(): String {
        return """[
            {
                "locale": "mx",
                "loan": {
                "status": "due",
                "level": "silver",
                "due": 5600,
                "dueDate": 1563256800000
            },
                "timestamp": 1561253056482,
                "username": "test MX"
            }
        ]"""
    }

    private fun getLocaleTestData(): String {
        return """{
              "ke": {
                "currency": "KSh ",
                "loanLimit": 30000,
                "timezone": 10800000
              },
              "mx": {
                "currency": "${'$'}",
                "loanLimit": 10000,
                "timezone": -21600000
              },
              "ph": {
                "currency": "Php ",
                "loanLimit": 25000,
                "timezone": 28800000
              }
            }"""
    }

}