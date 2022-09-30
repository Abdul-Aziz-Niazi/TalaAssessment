package com.abdulaziz.tala.home.domain

import androidx.lifecycle.ViewModel
import com.abdulaziz.tala.home.data.LoanResponseItem
import com.abdulaziz.tala.home.data.LocaleData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getLoanStatusUseCase: GetLoanStatusUseCase,
    private val getLocaleDataUseCase: GetLocaleDataUseCase
) : ViewModel() {

    fun getLoanData() = getLoanStatusUseCase.execute()
    fun getLocaleData() = getLocaleDataUseCase.execute()
    fun getCurrencyForLoanData(loanItem: LoanResponseItem, localeData:LocaleData?) = when (loanItem.locale) {
        "ke" -> localeData?.ke?.currency.orEmpty()
        "mx" -> localeData?.mx?.currency.orEmpty()
        "ph" -> localeData?.ph?.currency.orEmpty()
        else -> localeData?.ke?.currency.orEmpty()
    }
}