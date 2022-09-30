package com.abdulaziz.tala.home.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.abdulaziz.tala.home.data.LoanResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoanStatusViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {
    val loanResponseItem : LoanResponseItem? by lazy {
        savedStateHandle["loan_item"]
    }
}