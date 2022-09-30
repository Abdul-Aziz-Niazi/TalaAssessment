package com.abdulaziz.tala.home.domain

import androidx.lifecycle.MutableLiveData
import com.abdulaziz.tala.home.data.DataRepository
import com.abdulaziz.tala.home.data.LocaleData
import com.abdulaziz.tala.managers.showError
import com.abdulaziz.tala.managers.unzip
import javax.inject.Inject

interface GetLocaleDataUseCase {
    fun execute(): MutableLiveData<LocaleData>
}

class DefaultGetLocaleDataUseCase @Inject constructor(
    private val repository: DataRepository
) : GetLocaleDataUseCase {
    private val localeLiveData = MutableLiveData<LocaleData>()
    override fun execute(): MutableLiveData<LocaleData> {
        unzip({ repository.getLocaleData() }, { data ->
            localeLiveData.postValue(data)
        }) { error ->
            localeLiveData.postValue(LocaleData.EMPTY)
            error.showError()
        }
        return localeLiveData
    }
}