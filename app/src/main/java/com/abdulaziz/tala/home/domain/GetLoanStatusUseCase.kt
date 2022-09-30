package com.abdulaziz.tala.home.domain

import androidx.lifecycle.MutableLiveData
import com.abdulaziz.tala.home.data.DataRepository
import com.abdulaziz.tala.home.data.LoanResponse
import com.abdulaziz.tala.managers.showError
import com.abdulaziz.tala.managers.unzip
import javax.inject.Inject

interface GetLoanStatusUseCase {
    fun execute(): MutableLiveData<LoanResponse>
}

class DefaultGetLoanStatusUseCase @Inject constructor(
    private val repository: DataRepository
) : GetLoanStatusUseCase {
    private val loanLiveData = MutableLiveData<LoanResponse>()
    override fun execute(): MutableLiveData<LoanResponse> {
        unzip({ repository.getLoanData() }, { loanData->
            //Store Data or manipulate as needed
            loanLiveData.postValue(loanData)
        }) {
            //Send Error
            loanLiveData.postValue(LoanResponse.EMPTY)
            it.showError()
        }
        return loanLiveData
    }
}
